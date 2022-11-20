package com.lld.carrental.strategies;

import com.lld.carrental.model.common.Booking;
import com.lld.carrental.model.vehicle.Vehicle;

public interface PricingStrategy {
    double getPrice(Booking booking, Vehicle vehicle);
}
