package com.lld.carrental.services.notification.bookingReminder;

import com.lld.carrental.model.account.User;
import com.lld.carrental.model.enums.NotificationStatus;
import com.lld.carrental.model.reservation.ReservationReminder;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.repository.UserRepository;
import com.lld.carrental.repository.VehicleReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class BookingReminderServiceImpl implements BookingReminderService {

    @Autowired
    VehicleReservationRepository vehicleReservationRepository;

    @Override
    public void notifyUser(ReservationReminder reservationReminder) {
        VehicleReservation vehicleReservation =
                vehicleReservationRepository.getVehicleReservation(reservationReminder.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        System.out.println("Notified user" + user.getContact().getEmail());
        reservationReminder.setNotificationStatus(NotificationStatus.SENT);
    }
}
