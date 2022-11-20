package com.lld.carrental.repository;

import com.lld.carrental.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleRepository {
    public static Map<String, Vehicle> vehicleMap = new HashMap<>();
    public static List<Vehicle> vehicles = new ArrayList<>();

    public Vehicle addVehicle(Vehicle hireableVehicle) {
        vehicleMap.putIfAbsent(hireableVehicle.getId(), hireableVehicle);
        vehicles.add(hireableVehicle);
        return hireableVehicle;
    }

}
