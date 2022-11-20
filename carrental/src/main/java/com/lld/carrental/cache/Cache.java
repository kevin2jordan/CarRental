package com.lld.carrental.cache;

import com.lld.carrental.exceptions.DoesNotExistException;
import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.model.vehicle.Vehicle;

import java.util.List;

public interface Cache {

    public List<String> getVehiclesByBranchId(String key);

    public List<String> getVehiclesIdByVehicleType(VehicleType vehicleType);

    public void addVehicleToaBranchId(String branchId, Vehicle vehicle);

    public void removeVehicleFromABranchId(String branchId, Vehicle vehicle) throws DoesNotExistException;
}
