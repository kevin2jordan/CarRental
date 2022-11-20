package com.lld.carrental.services.booking;


import com.lld.carrental.exceptions.DoesNotExistException;

public interface BookingService {

    double bookVehicle(String branchId, String type, Integer startTime, Integer endTime) throws DoesNotExistException;

}