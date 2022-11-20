package com.lld.carrental.services.notification.bookingReminder;

import com.lld.carrental.model.reservation.ReservationReminder;

public interface BookingReminderService {
    void notifyUser(ReservationReminder reservationReminder);
}
