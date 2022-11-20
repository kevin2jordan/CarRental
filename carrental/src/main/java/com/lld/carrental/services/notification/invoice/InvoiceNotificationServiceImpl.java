package com.lld.carrental.services.notification.invoice;

import com.lld.carrental.model.account.User;
import com.lld.carrental.model.enums.NotificationStatus;
import com.lld.carrental.model.reservation.InvoiceNotification;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.repository.UserRepository;
import com.lld.carrental.repository.VehicleReservationRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class InvoiceNotificationServiceImpl implements InvoiceNotificationService {

    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap
                .get(invoiceNotification.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
