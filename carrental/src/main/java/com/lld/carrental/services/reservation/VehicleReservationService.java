package com.lld.carrental.services.reservation;

import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.model.enums.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleReservationService {
    List<VehicleReservation> getReservations(VehicleType vehicleType);

    boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate);
}
