package com.lld.carrental.services;

import com.lld.carrental.exceptions.VehicleDoesNotExistsException;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.services.vehicle.VehicleService;
import com.lld.carrental.services.vehicle.VehicleServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleServiceTest {

    @Test
    public void addVehicle() {
        VehicleService vehicleService = new VehicleServiceImpl();
        Vehicle vehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        assertNotNull(vehicle);
    }

    @Test
    public void updateQrCode() throws VehicleDoesNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        Vehicle vehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        String qrCode = UUID.randomUUID().toString();
        vehicle.setQrCode(qrCode);
        vehicleService.updateQrCode(vehicle.getId(), qrCode);
        Vehicle altered = vehicleService.getVehicleById(vehicle.getId());
        assertEquals(qrCode, altered.getQrCode());
    }

    @Test
    public void getVehicleById() {
        VehicleService vehicleService = new VehicleServiceImpl();
        Vehicle vehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        Vehicle vehicleDetails= vehicleService.getVehicleById(vehicle.getId());
        assertNotNull(vehicleDetails);
    }

    @Test
    public void getVehicleByQrCode() {
        VehicleService vehicleService = new VehicleServiceImpl();
        Vehicle vehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        Vehicle vehicleDetails = vehicleService.getVehicleByQrCode(vehicle.getQrCode());
        assertNotNull(vehicleDetails);
    }

    @Test
    public void removeVehicle() throws VehicleDoesNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        Vehicle vehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        vehicleService.removeVehicle(vehicle.getId());
        Vehicle vehicleDetails = vehicleService.getVehicleById(vehicle.getId());
        assertNull(vehicleDetails);
    }
}
