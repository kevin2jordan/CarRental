package com.lld.carrental.strategies;

import com.lld.carrental.model.reservation.VehicleReservation;

public interface VehicleAllocationStrategy {
    public VehicleReservation getVehicle();
}
