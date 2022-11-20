package com.lld.carrental.services.user;

import com.lld.carrental.exceptions.InvalidVehicleIdException;
import com.lld.carrental.exceptions.ReservationNotFoundException;
import com.lld.carrental.exceptions.VehicleAlreadyBookedException;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.model.vehicle.VehicleLocation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface UserService {
    VehicleReservation scanToBookAVehicle(String qrCode, String userId) throws InvalidVehicleIdException, VehicleAlreadyBookedException;

    VehicleReservation reserveAVehicle(VehicleReservation vehicleReservation);

    VehicleReservation cancelBooking(String reservationId);

    Vehicle pickupVehicle(VehicleReservation vehicleReservation);

    void returnVehicle(String reservationId, VehicleLocation vehicleLocation) throws ReservationNotFoundException;

    List<Vehicle> getBookedVehiclesByUserId(String userId);

    List<Vehicle> getBookedVehiclesForaSlot(String userId, LocalDateTime startDate,
                                   LocalDateTime endDate);
}
