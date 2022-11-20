package com.lld.carrental.repository;

import com.lld.carrental.model.reservation.VehicleInventory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleInventoryRepository {
    public static List<VehicleInventory> vehicleInventoryList = new ArrayList<>();

    public VehicleInventory addToInventory(VehicleInventory vehicleInventory) {
        vehicleInventoryList.add(vehicleInventory);
        return vehicleInventory;
    }

    public void removeFromInventory(VehicleInventory vehicleInventory) {
        vehicleInventoryList.removeIf(vi -> vi.getVehicle().getQrCode().equalsIgnoreCase(
                vehicleInventory.getVehicle().getQrCode()
        ));
    }
}
