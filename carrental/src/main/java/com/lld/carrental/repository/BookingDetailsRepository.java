package com.lld.carrental.repository;

import com.lld.carrental.model.common.Booking;
import com.lld.carrental.model.reservation.VehicleInventory;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailsRepository {
    public static List<Booking> bookingDetailsList = new ArrayList<>();

    public Booking addBookingDetails(Booking booking) {
        bookingDetailsList.add(booking);
        return booking;
    }
}
