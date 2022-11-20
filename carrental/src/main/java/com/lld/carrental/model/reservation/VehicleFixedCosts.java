package com.lld.carrental.model.reservation;

import com.lld.carrental.model.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;


public class VehicleFixedCosts {
    public static Map<VehicleType, Double> vehicleFixedCost = new HashMap<>();

    static {
        vehicleFixedCost.put(VehicleType.HATCHBACK, 100.0);
        vehicleFixedCost.put(VehicleType.SEDAN, 100.0);
        vehicleFixedCost.put(VehicleType.SUV, 100.0);
        vehicleFixedCost.put(VehicleType.TRUCK, 100.0);
        vehicleFixedCost.put(VehicleType.VAN, 100.0);
    }
}
