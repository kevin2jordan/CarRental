package com.lld.carrental.services.invoice;

import com.lld.carrental.model.enums.VehicleReservationType;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceFactory {
    public InvoiceService getInvoiceService(VehicleReservationType vehicleReservationType) {
        switch (vehicleReservationType) {
            case FOUR_HOURS:
            case EIGHT_HOURS:
                return new PackageInvoiceServiceImpl();
            case DAY:
                return new DailyInvoiceService();
            case MONTH:
                return new MonthlyInvoiceService();
            case HOURLY:
            default:
                return new HourlyInvoiceService();
        }
    }
}
