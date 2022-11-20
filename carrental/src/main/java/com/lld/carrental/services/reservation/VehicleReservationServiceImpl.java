package com.lld.carrental.services.reservation;

import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.repository.VehicleInventoryRepository;
import com.lld.carrental.repository.VehicleReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Primary
public class VehicleReservationServiceImpl implements VehicleReservationService {
    @Autowired
    private VehicleReservationRepository vehicleReservationRepository;

    @Override
    public List<VehicleReservation> getReservations(VehicleType vehicleType) {
        return vehicleReservationRepository.getVehicleReservations(vehicleType);
    }

    @Override
    public boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate) {
        return VehicleInventoryRepository.vehicleInventoryList
                .stream().anyMatch(vehicleInventory ->
                        vehicleInventory.getVehicle().getId().equalsIgnoreCase(qrCode) &&
                                ((vehicleInventory.getDueDate() != null &&
                                        fromDate.isBefore(vehicleInventory.getDueDate()))
                                        && (vehicleInventory.getFromDate() != null
                                        && toDate.isAfter(vehicleInventory.getFromDate()))));
    }
}
