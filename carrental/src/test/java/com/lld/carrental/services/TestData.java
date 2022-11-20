package com.lld.carrental.services;

import com.lld.carrental.model.account.ContactDetails;
import com.lld.carrental.model.account.LicenseInfo;
import com.lld.carrental.model.account.User;
import com.lld.carrental.model.common.Address;
import com.lld.carrental.model.common.Coordinates;
import com.lld.carrental.model.enums.ReservationStatus;
import com.lld.carrental.model.enums.VehicleReservationType;
import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.model.reservation.*;
import com.lld.carrental.model.vehicle.*;

import java.time.LocalDateTime;
import java.util.*;

public class TestData {
    public static Vehicle getTruck() {
        Vehicle truck = new Car();
        String id = UUID.randomUUID().toString();
        truck.setId(id);
        truck.setLicensePlateNumber("ka05ma8390");
        truck.setQrCode(id);
        truck.setBrandName("Mahindra");
        truck.setModel("Bolero");
        truck.setYearOfManufacture(2015);
        truck.setMileage(95910);
        truck.setNumberOfSeats(5);
        truck.setVehicleCategory(VehicleCategory.COMMERCIAL);
        truck.setVehicleStatus(VehicleStatus.AVAILALBE);
        truck.setVehicleType(VehicleType.TRUCK);
        VehicleLocation vehicleLocation = new VehicleLocation();
        vehicleLocation.setAddress(getAddress());
        vehicleLocation.setCoordinates(getCoordinates());
        truck.setParkedLocation(vehicleLocation);
        return truck;
    }

    public static Vehicle getHatchBack() {
        Vehicle car = new Car();
        String id = UUID.randomUUID().toString();
        car.setId(id);
        car.setLicensePlateNumber("ka51ca8344");
        car.setQrCode(id);
        car.setBrandName("Hyundai");
        car.setModel("i20");
        car.setYearOfManufacture(2018);
        car.setMileage(15010);
        car.setNumberOfSeats(5);
        car.setVehicleCategory(VehicleCategory.PASSENGER);
        car.setVehicleStatus(VehicleStatus.AVAILALBE);
        car.setVehicleType(VehicleType.HATCHBACK);
        VehicleLocation vehicleLocation = new VehicleLocation();
        vehicleLocation.setAddress(getAddress());
        vehicleLocation.setCoordinates(getCoordinates());
        car.setParkedLocation(vehicleLocation);
        return car;
    }

    public static Vehicle getMarutiHatchBack() {
        Vehicle car = new Car();
        String id = UUID.randomUUID().toString();
        car.setId(id);
        car.setLicensePlateNumber("ka02ca7416");
        car.setQrCode(id);
        car.setBrandName("Maruti");
        car.setModel("Swift");
        car.setYearOfManufacture(2015);
        car.setMileage(55010);
        car.setNumberOfSeats(5);
        car.setVehicleCategory(VehicleCategory.PASSENGER);
        car.setVehicleStatus(VehicleStatus.AVAILALBE);
        car.setVehicleType(VehicleType.HATCHBACK);
        VehicleLocation vehicleLocation = new VehicleLocation();
        vehicleLocation.setAddress(getAddress());
        vehicleLocation.setCoordinates(getCoordinates());
        car.setParkedLocation(vehicleLocation);
        return car;
    }

    public static Vehicle getSuvCar() {
        Vehicle car = new Car();
        String id = UUID.randomUUID().toString();
        car.setId(id);
        car.setLicensePlateNumber("ka01aa4561");
        car.setQrCode(id);
        car.setBrandName("TATA");
        car.setModel("Harrier");
        car.setYearOfManufacture(2019);
        car.setMileage(12156);
        car.setNumberOfSeats(5);
        car.setVehicleCategory(VehicleCategory.PASSENGER);
        car.setVehicleStatus(VehicleStatus.AVAILALBE);
        car.setVehicleType(VehicleType.SUV);
        VehicleLocation vehicleLocation = new VehicleLocation();
        vehicleLocation.setAddress(getAddress());
        vehicleLocation.setCoordinates(getCoordinates());
        car.setParkedLocation(vehicleLocation);
        return car;
    }

    public static Address getAddress() {
        Address address = new Address();
        address.setStreet("Bannerghatta road parking");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipCode("600071");
        return address;
    }

    public static Coordinates getCoordinates() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(12.459454);
        coordinates.setLatitude(79.459454);
        return coordinates;
    }


    public static VehicleReservation buildHatchbackReservation() {
        Vehicle vehicle = getHatchBack();
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        VehicleReservation vehicleReservation = new VehicleReservation();
        vehicleReservation.setUsrId("user1");
        vehicleReservation.setReservationId(UUID.randomUUID().toString());
        vehicleReservation.setFromDate(LocalDateTime.now());
        vehicleReservation.setDueDate(LocalDateTime.now().plusDays(2));
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicleReservation.setStartMileage(vehicle.getMileage());
        vehicleReservation.setPickupLocation(
                vehicle.getParkedLocation().getAddress());

        vehicleReservation.setAllocatedVehicleId(vehicle.getId());
        vehicleReservation.setVehicleReservationType(VehicleReservationType.DAY);
        return vehicleReservation;
    }

    public static VehicleReservation buildMarutiHatchbackReservation() {
        Vehicle vehicle = getMarutiHatchBack();
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        VehicleReservation vehicleReservation = new VehicleReservation();
        vehicleReservation.setUsrId("user1");
        vehicleReservation.setReservationId(UUID.randomUUID().toString());
        vehicleReservation.setFromDate(LocalDateTime.now().plusDays(20));
        vehicleReservation.setDueDate(LocalDateTime.now().plusDays(25));
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicleReservation.setStartMileage(vehicle.getMileage());
        vehicleReservation.setPickupLocation(
                vehicle.getParkedLocation().getAddress());

        vehicleReservation.setAllocatedVehicleId(vehicle.getId());
        vehicleReservation.setVehicleReservationType(VehicleReservationType.DAY);
        return vehicleReservation;
    }

    public static List<VehicleInventory> buildVehicleInventory() {
        List<VehicleInventory> vehicleInventories = new ArrayList<>();
        vehicleInventories.add(new VehicleInventory(buildHatchbackReservation(),
                getHatchBack()));
        vehicleInventories.add(new VehicleInventory(buildMarutiHatchbackReservation(),
                getMarutiHatchBack()));
        return vehicleInventories;
    }

    public static List<Vehicle> getHireableVehicles() {
        List<Vehicle> hireableVehicles = new ArrayList<>();
        hireableVehicles.add(getHatchBack());
        hireableVehicles.add(getMarutiHatchBack());
        hireableVehicles.add(getSuvCar());
        hireableVehicles.add(getSuvCar());
        return hireableVehicles;
    }

    public static User getUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setAccountId(UUID.randomUUID().toString());
        user.setUserName("testusername" + new Random().nextInt());
        user.setPassword("testuserwpd");
        user.setLastAccessed(LocalDateTime.now().minusDays(2));
        user.setContact(getContact(email));
        return user;
    }

    public static ContactDetails getContact(String email) {
        ContactDetails contact = new ContactDetails();
        contact.setEmail(email);
        contact.setPhone("8745828882");
        contact.setAddress(getAddress());
        return contact;
    }


    public static VehicleReservation getVehicleReservation(User user) {
        List<Vehicle> hireableVehicles = getHireableVehicles();
        VehicleReservation vehicleReservation = new VehicleReservation();
        Vehicle vehicle = hireableVehicles.get(0);
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        vehicleReservation.setUsrId(user.getAccountId());
        vehicleReservation.setReservationId(UUID.randomUUID().toString());
        vehicleReservation.setFromDate(LocalDateTime.now());
        vehicleReservation.setDueDate(LocalDateTime.now().plusHours(2));
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicleReservation.setVehicleReservationType(VehicleReservationType.HOURLY);
        vehicleReservation.setVehicleType(vehicle.getVehicleType());
        vehicleReservation.setStartMileage(vehicle.getMileage());
        vehicleReservation.setPickupLocation(
                vehicle.getParkedLocation().getAddress());
        vehicleReservation.setVehicleReservationType(VehicleReservationType.DAY);
        return vehicleReservation;
    }

    public static LicenseInfo getLicenseInfo() {
        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.setLicenceNumber("ka051023098290");
        LocalDateTime issuedDate = LocalDateTime.now().minusYears(5);
        licenseInfo.setIssueDate(issuedDate);
        licenseInfo.setExpiryDate(issuedDate.plusYears(20));
        licenseInfo.setIssuedAtPlace("Jayanagar, Bangalore");
        licenseInfo.setIssuedInState("Karnataka");
        licenseInfo.setIssuedInCountry("India");
        return licenseInfo;
    }

}
