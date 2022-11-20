package com.lld.carrental.services.user;

import com.lld.carrental.exceptions.InvalidVehicleIdException;
import com.lld.carrental.exceptions.ReservationNotFoundException;
import com.lld.carrental.exceptions.VehicleAlreadyBookedException;
import com.lld.carrental.model.enums.NotificationStatus;
import com.lld.carrental.model.enums.ReservationStatus;
import com.lld.carrental.model.enums.VehicleReservationType;
import com.lld.carrental.model.reservation.*;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.model.vehicle.VehicleLocation;
import com.lld.carrental.model.vehicle.VehicleStatus;
import com.lld.carrental.repository.UserRepository;
import com.lld.carrental.repository.VehicleInventoryRepository;
import com.lld.carrental.repository.VehicleRepository;
import com.lld.carrental.repository.VehicleReservationRepository;
import com.lld.carrental.services.invoice.InvoiceService;
import com.lld.carrental.services.notification.invoice.InvoiceNotificationService;
import com.lld.carrental.services.reservation.VehicleReservationService;
import com.lld.carrental.services.search.VehicleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    VehicleReservationRepository vehicleReservationRepository;

    @Autowired
    VehicleReservationService vehicleReservationService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    InvoiceNotificationService invoiceNotificationService;

    @Autowired
    VehicleSearchService vehicleSearchService;

    @Autowired
    VehicleInventoryRepository vehicleInventoryRepository;

    @Override
    public VehicleReservation scanToBookAVehicle(String qrCode, String userId) throws InvalidVehicleIdException, VehicleAlreadyBookedException {
        if (VehicleRepository.vehicleMap.get(qrCode) == null) {
            throw new InvalidVehicleIdException("Invalid vehicle id.");
        }
        if (vehicleReservationService.isVehicleBooked(qrCode,
                LocalDateTime.now(), LocalDateTime.now().plusHours(2))) {
            throw new VehicleAlreadyBookedException("Vehicle booked. Try another vehicle.");
        }
        VehicleReservation vehicleReservation = buildQuickReservation(qrCode, userId);
        vehicleReservation = vehicleReservationRepository.reserve(vehicleReservation);
        updateVehicleInventory(vehicleReservation);
        return vehicleReservation;
    }

    @Override
    public VehicleReservation reserveAVehicle(VehicleReservation vehicleReservation) {
        vehicleReservation.setStatus(ReservationStatus.CONFIRMED);
        vehicleReservation.setReservationDate(LocalDateTime.now());
        vehicleReservation = vehicleReservationRepository.reserve(vehicleReservation);
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        updateVehicleInventory(vehicleReservation);
        return vehicleReservation;
    }

    @Override
    public VehicleReservation cancelBooking(String reservationId) {
        VehicleReservation vehicleReservation = VehicleReservationRepository
                .vehicleReservationMap.get(reservationId);
        Vehicle vehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAllocatedVehicleId());
        vehicleReservation.setStatus(ReservationStatus.CANCELLED);
        updateVehicleInventory(vehicleReservation);
        vehicleReservation.setDropLocation(vehicle.
                getParkedLocation().getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(vehicle.getMileage());
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        return vehicleReservation;
    }

    @Override
    public Vehicle pickupVehicle(VehicleReservation vehicleReservation) {
        Vehicle vehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAllocatedVehicleId());
        vehicleReservation.setStartMileage(vehicle.getMileage());
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicle.setVehicleStatus(VehicleStatus.INUSE);
        return vehicle;
    }

    @Override
    public void returnVehicle(String reservationId,
                              VehicleLocation vehicleLocation) throws ReservationNotFoundException {

        VehicleReservation vehicleReservation = vehicleReservationRepository
                .getVehicleReservation(reservationId);
        if (vehicleReservation == null) {
            throw new ReservationNotFoundException
                    ("Could not find reservation with id " + reservationId);
        }

        Vehicle vehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAllocatedVehicleId());
        vehicle.setParkedLocation(vehicleLocation);
        vehicle.setVehicleStatus(VehicleStatus.AVAILALBE);
        vehicleReservation.setStatus(ReservationStatus.COMPLETED);
        vehicleReservation.setDropLocation(vehicleLocation.getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(vehicle.getMileage());
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        updateVehicleInventory(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
    }

    @Override
    public List<Vehicle> getBookedVehiclesByUserId(String userId) {
        return userRepository.getHiredVehicles(userId);
    }

    @Override
    public List<Vehicle> getBookedVehiclesForaSlot(String userId, LocalDateTime startDate,
                                          LocalDateTime endDate) {
        return userRepository.getHiredVehicles(userId, startDate, endDate);
    }

    private VehicleReservation buildQuickReservation(String qrCode, String userId) {

        Vehicle vehicle = VehicleRepository.vehicleMap.get(qrCode);
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        VehicleReservation vehicleReservation = new VehicleReservation();
        vehicleReservation.setUsrId(userId);
        vehicleReservation.setReservationId(UUID.randomUUID().toString());
        vehicleReservation.setFromDate(LocalDateTime.now());
        vehicleReservation.setDueDate(LocalDateTime.now().plusHours(2));
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicleReservation.setVehicleReservationType(VehicleReservationType.HOURLY);
        vehicleReservation.setVehicleType(vehicle.getVehicleType());
        vehicleReservation.setStartMileage(vehicle.getMileage());
        vehicleReservation.setPickupLocation(
                vehicle.getParkedLocation().getAddress());

        Vehicle vehicleRepository = VehicleRepository.vehicleMap.get(qrCode);
        vehicleReservation.setAllocatedVehicleId(vehicleRepository.getId());
        vehicleReservation.setVehicleReservationType(VehicleReservationType.HOURLY);
        return vehicleReservation;
    }

    private InvoiceNotification buildInvoiceNotification(Invoice invoice) {
        InvoiceNotification invoiceNotification = new InvoiceNotification();
        invoiceNotification.setReservationId(invoice.getReservationId());
        invoiceNotification.setUserId(invoice.getUserId());
        invoiceNotification.setCreatedDate(LocalDateTime.now());
        invoiceNotification.setNotificationStatus(NotificationStatus.PENDING);
        return invoiceNotification;
    }

    private VehicleInventory buildVehicleInventory(VehicleReservation vehicleReservation) {
        Vehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAllocatedVehicleId());
        VehicleInventory vehicleInventory = new VehicleInventory(vehicleReservation,
                hireableVehicle);
        return vehicleInventory;
    }

    private void updateVehicleInventory(VehicleReservation vehicleReservation) {
        VehicleInventory vehicleInventory = buildVehicleInventory(vehicleReservation);
        vehicleInventoryRepository.addToInventory(vehicleInventory);
    }
}
