package com.lld.carrental.services.invoice;

import com.lld.carrental.model.account.User;
import com.lld.carrental.model.reservation.Invoice;
import com.lld.carrental.model.reservation.VehicleDailyCosts;
import com.lld.carrental.model.reservation.VehicleFixedCosts;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.repository.UserRepository;

import java.time.Duration;
import java.util.UUID;

public class DailyInvoiceService implements InvoiceService {

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusDays(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());
        double hours = Math.ceil(rentedDuration.toHours());

        double days = Math.ceil(hours / 24) + hours % 24;

        double dailyCost = VehicleDailyCosts.
                vehicleDailyCost.get(vehicleReservation.getVehicleType());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(vehicleReservation.getVehicleType());

        double rentalCost = days * dailyCost + fixedCost;
        double taxes = rentalCost * .18;

        invoice.setUsageCharges(rentalCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(rentalCost + taxes);
        return invoice;
    }
}
