package com.lld.carrental.services.booking;

import com.lld.carrental.exceptions.DoesNotExistException;
import com.lld.carrental.model.common.Booking;
import com.lld.carrental.model.common.Branch;
import com.lld.carrental.model.vehicle.*;
import com.lld.carrental.strategies.PricingStrategy;

import java.util.*;

import static com.lld.carrental.services.utils.OverlapCheckUtil.checkOverlap;

public class BookingServiceImpl implements BookingService {

    private PricingStrategy pricingStrategy;

    private Map<String, Branch> branchMap = new Hashtable<>();

    private Map<String, Map<String, List<Booking>>> branchBookingMap = new HashMap<>();
    int count=1;


    @Override
    public double bookVehicle(String branchId, String type, Integer startTime, Integer endTime) throws DoesNotExistException {
        if (!branchMap.containsKey(branchId)) {
            throw new DoesNotExistException("Branch id does not exist");
        }
        Branch branch = branchMap.get(branchId);
        if(!branch.getVehicleTypes().contains(type)) {
            throw new DoesNotExistException("Vehicle type does not exist");
        }
        Map<String, List<Booking>> bookingMap = branchBookingMap.get(branchId);

        Integer totalVehicleType = 0;
        Integer available=0;
        for (Vehicle it1 : branch.getVehicleList()) {
            if (it1.getVehicleType().equals(type)) {
                totalVehicleType++;
                if (bookingMap.getOrDefault(it1.getId(), new ArrayList<>()).stream()
                        .noneMatch(it -> checkOverlap(it, startTime, endTime)))
                    available++;
            }
        }

        System.out.println("Booking started --:");

        for (Vehicle item : branch.getVehicleList()) {
            if (!item.getVehicleType().equals(type)) {
                continue;
            }
            synchronized (item) {

                System.out.println("Taking lock on item = " + item);
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Booking> bookings = bookingMap.getOrDefault(item.getId(), new ArrayList<>());
                if (bookings.stream().noneMatch(it -> checkOverlap(it, startTime, endTime))) {
                    Booking booking = new Booking(count++, branchId, item.getId(), startTime, endTime,
                            0.0);
                    double price = pricingStrategy.getPrice(booking, item);
                    booking.setPrice(price);
                    bookings.add(booking);
                    bookingMap.put(item.getId(), bookings);
                    System.out.println("Booking completed with details "+ booking);
                    return booking.getPrice();
                }
            }

        }
        return -1;
    }


}