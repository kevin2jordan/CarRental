package com.lld.carrental.services.utils;

import com.lld.carrental.model.account.User;
import com.lld.carrental.model.reservation.Invoice;
import com.lld.carrental.model.reservation.VehicleFixedCosts;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.repository.UserRepository;
import com.lld.carrental.repository.VehicleRepository;

import java.util.UUID;

public class InvoiceBuilderUtil {
    public static Invoice buildCancelledInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Vehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAllocatedVehicleId());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());
        double taxes = fixedCost * .18;
        invoice.setUsageCharges(fixedCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(fixedCost + taxes);
        return invoice;
    }
}
