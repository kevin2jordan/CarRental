package com.lld.carrental.model.vehicle;

import com.lld.carrental.model.common.Coordinates;
import com.lld.carrental.model.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Vehicle {
    private String id;
    private String licensePlateNumber;
    private String qrCode;
    private String brandName;
    private String model;
    private int yearOfManufacture;
    private double mileage;
    private int numberOfSeats;
    private VehicleCategory vehicleCategory;
    private VehicleStatus vehicleStatus;
    private VehicleType vehicleType;
    private VehicleLocation parkedLocation;
    private Coordinates currentLocation;
}
