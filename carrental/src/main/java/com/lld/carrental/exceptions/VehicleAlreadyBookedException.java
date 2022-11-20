package com.lld.carrental.exceptions;

public class VehicleAlreadyBookedException extends Exception {
    public VehicleAlreadyBookedException(String message) {
        super(message);
    }
}
