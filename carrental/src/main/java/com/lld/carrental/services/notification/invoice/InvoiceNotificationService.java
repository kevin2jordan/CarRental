package com.lld.carrental.services.notification.invoice;

import com.lld.carrental.model.reservation.InvoiceNotification;
import org.springframework.stereotype.Service;

public interface InvoiceNotificationService {
    void notifyUser(InvoiceNotification invoice);
}
