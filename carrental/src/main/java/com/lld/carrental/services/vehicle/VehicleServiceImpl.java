package com.lld.carrental.services.vehicle;

import com.lld.carrental.exceptions.AlreadyExistException;
import com.lld.carrental.exceptions.DoesNotExistException;
import com.lld.carrental.exceptions.VehicleDoesNotExistsException;
import com.lld.carrental.model.common.Booking;
import com.lld.carrental.model.common.Branch;
import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.model.reservation.VehicleInventory;
import com.lld.carrental.model.vehicle.*;
import com.lld.carrental.repository.VehicleInventoryRepository;
import com.lld.carrental.repository.VehicleRepository;

import java.util.*;

import static com.lld.carrental.services.utils.OverlapCheckUtil.checkOverlap;

public class VehicleServiceImpl implements VehicleService {
    VehicleRepository vehicleRepository = new VehicleRepository();

    private Map<String, Branch> branchMap = new Hashtable<>();

    private Map<String, Map<String, List<Booking>>> branchBookingMap = new HashMap<>();

    @Override
    public Vehicle getVehicleById(String id) {
        return VehicleRepository.vehicleMap.get(id);
    }

    @Override
    public Vehicle getVehicleByQrCode(String qrCode) {
        return VehicleRepository.vehicles.stream().filter(hireableVehicle ->
                hireableVehicle.getQrCode().equalsIgnoreCase(qrCode)).findAny().get();
    }

    @Override
    public Vehicle addVehicle(Vehicle hireableVehicle) {
        addToInventory(hireableVehicle);
        return vehicleRepository.addVehicle(hireableVehicle);
    }

    @Override
    public void updateQrCode(String vehicleId, String qrCode) throws VehicleDoesNotExistsException {
        Vehicle hireableVehicle = VehicleRepository.vehicleMap.get(vehicleId);
        if (hireableVehicle == null)
            throw new VehicleDoesNotExistsException("Vehicle with id " + vehicleId + "not found");
        hireableVehicle.setQrCode(qrCode);
    }

    @Override
    public void removeVehicle(String vehicleId) throws VehicleDoesNotExistsException {
        Vehicle hireableVehicle = VehicleRepository.vehicleMap.get(vehicleId);
        if (hireableVehicle == null)
            throw new VehicleDoesNotExistsException("Vehicle with id " + vehicleId + "not found");
        VehicleRepository.vehicleMap.remove(vehicleId);
        VehicleInventoryRepository vehicleInventoryRepository = new VehicleInventoryRepository();
        vehicleInventoryRepository.removeFromInventory(new VehicleInventory(hireableVehicle));
        //Remove future bookings or reassign
    }

    private void addToInventory(Vehicle hireableVehicle) {
        VehicleInventory vehicleInventory = new VehicleInventory(hireableVehicle);
        VehicleInventoryRepository vehicleInventoryRepository = new VehicleInventoryRepository();
        vehicleInventoryRepository.addToInventory(vehicleInventory);
    }
    @Override
    public Boolean addBranch(String id, Set<String> vehicleTypes) throws AlreadyExistException {
        if (branchMap.containsKey(id)) {
            throw new AlreadyExistException("Branch already exists ");
        }
        Branch branch = new Branch(id, vehicleTypes, new TreeSet<>());
        branchMap.put(id, branch);
        branchBookingMap.put(id, new HashMap<>());
        return true;
    }

    @Override
    public Boolean addVehicle(String branchId, String type, String vehicleNumber, String brandName,
                              String model, VehicleLocation vehicleLocation) throws DoesNotExistException {
        if (!branchMap.containsKey(branchId)) {
            throw new DoesNotExistException("Branch id does not exist");
        }
        Branch branch = branchMap.get(branchId);
        if(!branch.getVehicleTypes().contains(type)) {
            throw new DoesNotExistException("Vehicle type does not exist");
        }
        Vehicle car = new Car();
        String id = UUID.randomUUID().toString();
        car.setId(id);
        car.setLicensePlateNumber(vehicleNumber);
        car.setQrCode(id);
        car.setBrandName(brandName);
        car.setModel(model);
        car.setYearOfManufacture(2015);
        car.setMileage(95910);
        car.setNumberOfSeats(5);
        car.setVehicleCategory(VehicleCategory.COMMERCIAL);
        car.setVehicleStatus(VehicleStatus.AVAILALBE);
        car.setVehicleType(VehicleType.TRUCK);
        car.setParkedLocation(vehicleLocation);
        branch.getVehicleList().add(car);
        branchBookingMap.get(branchId).put(id, new ArrayList<>());
        return true;
    }

    @Override
    public List<Vehicle> getAvailableVehicle(String branchId, Integer startTime, Integer endTime) {
        List<Vehicle> ans = new ArrayList<>();
        Branch branch = branchMap.get(branchId);
        Map<String, List<Booking>> bookingMap = branchBookingMap.get(branchId);

        for (Vehicle item : branch.getVehicleList()) {
            List<Booking> bookings = bookingMap.getOrDefault(item.getId(), new ArrayList<>());
            Boolean match = bookings.stream().anyMatch(it -> checkOverlap(it, startTime, endTime));
            if (!match) {
                ans.add(item);
            }
        }
        return ans;
    }

}
