package com.lld.carrental.services.invoice;

import com.lld.carrental.model.account.User;
import com.lld.carrental.model.reservation.Invoice;
import com.lld.carrental.model.reservation.VehicleFixedCosts;
import com.lld.carrental.model.reservation.VehicleMonthlyCosts;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.repository.UserRepository;
import com.lld.carrental.repository.VehicleRepository;

import java.time.Duration;
import java.util.UUID;

public class MonthlyInvoiceService implements InvoiceService {
    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusMonths(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());

        double hours = Math.ceil(rentedDuration.toHours());

        double days = Math.ceil(hours / 24) + hours % 24;

        double months = Math.ceil(days / 30);

        Vehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAllocatedVehicleId());

        double monthlyCost = VehicleMonthlyCosts.
                vehicleMonthlyCost.get(hireableVehicle.getVehicleType());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());

        double rentalCost = months * monthlyCost + fixedCost;
        double taxes = rentalCost * .18;

        invoice.setUsageCharges(rentalCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(rentalCost + taxes);
        return invoice;
    }
}
