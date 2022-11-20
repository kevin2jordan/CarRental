package com.lld.carrental.services.search;

import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.repository.VehicleInventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleSearchServiceImpl implements VehicleSearchService {

    @Override
    public List<Vehicle> searchByVehicleType(VehicleType vehicleType, String city,
                                LocalDateTime fromDate, LocalDateTime toDate) {
        return VehicleInventoryRepository.vehicleInventoryList
                .stream()
                .filter(vehicleInventory ->
                        vehicleInventory.getVehicle().getVehicleType() == vehicleType
                                && vehicleInventory.getVehicle().getParkedLocation().getAddress()
                                .getCity().equalsIgnoreCase(city)
                                && !(
                                (vehicleInventory.getDueDate() != null &&
                                        fromDate.isBefore(vehicleInventory.getDueDate()))
                                        && (vehicleInventory.getFromDate() != null
                                        && toDate.isAfter(vehicleInventory.getFromDate())))
                ).map(vehicleInventory -> vehicleInventory.getVehicle())
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> searchByModel(String brandName, String model, String city,
                                LocalDateTime fromDate, LocalDateTime toDate) {
        return VehicleInventoryRepository.vehicleInventoryList
                .stream()
                .filter(vehicleInventory ->
                        vehicleInventory.getVehicle().getBrandName().equalsIgnoreCase(brandName)
                                && vehicleInventory.getVehicle().getModel().equalsIgnoreCase(model)
                                && vehicleInventory.getVehicle().getParkedLocation().getAddress()
                                .getCity().equalsIgnoreCase(city)
                                && !(
                                (vehicleInventory.getDueDate() != null &&
                                        fromDate.isBefore(vehicleInventory.getDueDate()))
                                        && (vehicleInventory.getFromDate() != null
                                        && toDate.isAfter(vehicleInventory.getFromDate())))
                ).map(vehicleInventory -> vehicleInventory.getVehicle())
                .collect(Collectors.toList());
    }


}
