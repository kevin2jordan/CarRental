package com.lld.carrental.model.reservation;

import com.lld.carrental.model.enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceNotification {
    private String reservationId;
    private String userId;
    private LocalDateTime createdDate;
    private NotificationStatus notificationStatus;
}
