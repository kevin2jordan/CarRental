package com.lld.carrental.exceptions;

public class VehicleDoesNotExistsException extends Exception {
    public VehicleDoesNotExistsException(String message) {
        super(message);
    }
}
