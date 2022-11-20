package com.lld.carrental.services.utils;

import com.lld.carrental.model.common.Booking;

public class OverlapCheckUtil {
    public static boolean checkOverlap(Booking it, Integer startTime, Integer endTime) {
        return Math.max(it.getStartTime(), startTime) < Math.min(endTime, it.getEndTime());
    }
}
