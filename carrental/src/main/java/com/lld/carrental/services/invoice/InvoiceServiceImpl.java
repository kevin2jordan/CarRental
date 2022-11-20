package com.lld.carrental.services.invoice;

import com.lld.carrental.model.enums.ReservationStatus;
import com.lld.carrental.model.reservation.Invoice;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.services.utils.InvoiceBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceServiceFactory invoiceServiceFactory;

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        if (vehicleReservation.getStatus() == ReservationStatus.CANCELLED)
            return InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);
        return invoiceServiceFactory.getInvoiceService(vehicleReservation.getVehicleReservationType())
                .computeInvoice(vehicleReservation);
    }
}
