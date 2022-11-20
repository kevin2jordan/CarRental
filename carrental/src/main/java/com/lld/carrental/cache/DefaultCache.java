package com.lld.carrental.cache;

import com.lld.carrental.exceptions.DoesNotExistException;
import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.repository.CacheRepository;

import java.util.List;

public class DefaultCache implements Cache{

    @Override
    public List<String> getVehiclesByBranchId(String branchId) {
        return CacheRepository.getVehiclesIdByBranchId(branchId);
    }

    @Override
    public List<String> getVehiclesIdByVehicleType(VehicleType type) {
        return CacheRepository.getVehiclesIdByVehicleType(type);
    }

    @Override
    public void addVehicleToaBranchId(String branchId, Vehicle vehicle) {
        CacheRepository.getVehiclesIdByBranchId(branchId).add(vehicle.getId());
    }

    @Override
    public void removeVehicleFromABranchId(String branchId, Vehicle vehicle) throws DoesNotExistException {
        List<String> vehicles = CacheRepository.getVehiclesIdByBranchId(branchId);
        if(vehicles.contains(vehicle.getId())) {
            vehicles.remove(vehicle.getId());
        } else {
            throw new DoesNotExistException("Vehicle does not exist");
        }
    }


}
