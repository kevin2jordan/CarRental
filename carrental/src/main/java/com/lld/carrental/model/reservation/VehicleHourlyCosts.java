package com.lld.carrental.model.reservation;

import com.lld.carrental.model.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;


public class VehicleHourlyCosts {
    public static Map<VehicleType, Double> vehicleHourlyCost = new HashMap<>();

    static {
        vehicleHourlyCost.put(VehicleType.HATCHBACK, 100.0);
        vehicleHourlyCost.put(VehicleType.SEDAN, 100.0);
        vehicleHourlyCost.put(VehicleType.SUV, 100.0);
        vehicleHourlyCost.put(VehicleType.TRUCK, 100.0);
        vehicleHourlyCost.put(VehicleType.VAN, 100.0);
    }
}
