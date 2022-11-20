package com.lld.carrental.strategies;

import com.lld.carrental.model.common.Booking;
import com.lld.carrental.model.reservation.VehicleFixedCosts;
import com.lld.carrental.model.vehicle.Vehicle;

public class DefaultPricingStrategy implements PricingStrategy{
    @Override
    public double getPrice(Booking booking, Vehicle vehicle) {
        return VehicleFixedCosts.vehicleFixedCost.get(vehicle.getVehicleType())* (booking.getEndTime() - booking.getStartTime());
    }
}
