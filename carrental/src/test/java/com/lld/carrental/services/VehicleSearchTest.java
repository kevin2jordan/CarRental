package com.lld.carrental.services;

import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.repository.VehicleInventoryRepository;
import com.lld.carrental.services.search.VehicleSearchService;
import com.lld.carrental.services.search.VehicleSearchServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleSearchTest {

    @Test
    public void getVehicleByVehicleType() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<Vehicle> vehicleList = vehicleSearchService.searchByVehicleType(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4));
        assertEquals(2, vehicleList.size());
    }

    @Test
    public void getOneVehicleByVehicleType() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setFromDate(LocalDateTime.now());
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setDueDate(LocalDateTime.now().plusDays(4));

        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<Vehicle> vehicleList = vehicleSearchService.searchByVehicleType(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(3));
        assertEquals(1, vehicleList.size());
    }

    @Test
    public void getNoVehicleByVehicleType() {

        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setFromDate(LocalDateTime.now().minusDays(1));
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setDueDate(LocalDateTime.now().plusDays(4));

        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(1)
                .setFromDate(LocalDateTime.now().minusHours(5));
        VehicleInventoryRepository.vehicleInventoryList.get(1)
                .setDueDate(LocalDateTime.now().plusDays(2));

        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<Vehicle> vehicleList = vehicleSearchService.searchByVehicleType(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(0, vehicleList.size());
    }

    @Test
    public void getNoVehicleByModel() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<Vehicle> vehicleList = vehicleSearchService.searchByModel( "maker", "model",
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(0, vehicleList.size());
    }

    @Test
    public void getOneVehicleByModel() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<Vehicle> vehicleList = vehicleSearchService.searchByModel( "Maruti", "Swift",
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertEquals(1, vehicleList.size());
    }
}
