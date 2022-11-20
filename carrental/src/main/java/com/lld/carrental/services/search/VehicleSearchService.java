package com.lld.carrental.services.search;

import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.model.enums.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleSearchService {
    List<Vehicle> searchByVehicleType(VehicleType vehicleType, String city,
                         LocalDateTime fromDate, LocalDateTime toDate);

    List<Vehicle> searchByModel(String maker, String model, String city,
                         LocalDateTime fromDate, LocalDateTime toDate);

    //List<Vehicle> search(String crzId,)
}
