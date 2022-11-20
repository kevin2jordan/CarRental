package com.lld.carrental.services.vehicle;

import com.lld.carrental.exceptions.AlreadyExistException;
import com.lld.carrental.exceptions.DoesNotExistException;
import com.lld.carrental.exceptions.VehicleDoesNotExistsException;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.model.vehicle.VehicleLocation;

import java.util.List;
import java.util.Set;

public interface VehicleService {

    Boolean addBranch(String id, Set<String> vehicleTypes) throws AlreadyExistException;
    Boolean addVehicle(String branchId, String type, String vehicleNumber, String brandName,
                       String model, VehicleLocation vehicleLocation) throws DoesNotExistException;
    Vehicle getVehicleById(String id);

    Vehicle getVehicleByQrCode(String qrCode);

    Vehicle addVehicle(Vehicle hireableVehicle);

    void updateQrCode(String vehicleId, String qrCode) throws VehicleDoesNotExistsException;

    void removeVehicle(String vehicleId) throws VehicleDoesNotExistsException;

    List<Vehicle> getAvailableVehicle(String branchId, Integer startTime, Integer endTime);
}
