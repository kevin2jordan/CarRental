package com.lld.carrental.model.reservation;

import com.lld.carrental.model.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;


public class VehicleDailyCosts {
    public static Map<VehicleType, Double> vehicleDailyCost = new HashMap<>();

    static {
        vehicleDailyCost.put(VehicleType.HATCHBACK, 800.0);
        vehicleDailyCost.put(VehicleType.SEDAN, 1000.0);
        vehicleDailyCost.put(VehicleType.SUV, 1500.0);
        vehicleDailyCost.put(VehicleType.TRUCK, 2500.0);
        vehicleDailyCost.put(VehicleType.VAN, 1000.0);
    }
}
