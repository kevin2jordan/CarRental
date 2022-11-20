package com.lld.carrental.services.invoice;

import com.lld.carrental.model.reservation.Invoice;
import com.lld.carrental.model.reservation.VehicleReservation;
import org.springframework.stereotype.Service;

public interface InvoiceService {
    Invoice computeInvoice(VehicleReservation vehicleReservation);
}
