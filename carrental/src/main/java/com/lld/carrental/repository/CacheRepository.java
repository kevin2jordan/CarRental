package com.lld.carrental.repository;

import com.lld.carrental.model.enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheRepository {
    private static Map<String, List<String>> branchVehiclesMap = new HashMap<>();
    private static Map<VehicleType, List<String>> vehicleTypeListMap = new HashMap<>();

    public static List<String> getVehiclesIdByBranchId(String crzId) {
        return branchVehiclesMap.getOrDefault(crzId, new ArrayList<>());
    }

    public static List<String> getVehiclesIdByVehicleType(VehicleType type) {
        return vehicleTypeListMap.getOrDefault(type, new ArrayList<>());
    }
}
