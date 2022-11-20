package com.lld.carrental.repository;

import com.lld.carrental.exceptions.AccountDoesNotExistsException;
import com.lld.carrental.model.account.Account;
import com.lld.carrental.model.account.User;
import com.lld.carrental.model.reservation.VehicleReservation;
import com.lld.carrental.model.vehicle.Vehicle;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements AccountRepository {
    public static Map<String, User> userMap = new HashMap<>();
    public static Map<String, User> userUserIdMap = new HashMap<>();

    public Account createAccount(Account account) {
        userMap.putIfAbsent(account.getEmail(), (User) account);
        userUserIdMap.putIfAbsent(account.getAccountId(), (User) account);
        return account;
    }

    public void resetPassword(String userId, String password) throws AccountDoesNotExistsException {
        if (userMap.get(userId) == null)
            throw new AccountDoesNotExistsException("Account does not exist.");
        userMap.get(userId).setPassword(password);
    }

    public List<Vehicle> getHiredVehicles(String userId) {

        List<VehicleReservation> vehicleReservationList =
                VehicleReservationRepository.vehicleReservations
                        .stream().filter(vehicleReservation ->
                        vehicleReservation.getUsrId().equalsIgnoreCase(userId))
                        .collect(Collectors.toList());
        return vehicleReservationList.stream()
                .map(vehicleReservation ->
                        VehicleRepository.vehicleMap
                                .get(vehicleReservation.getAllocatedVehicleId()))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                          LocalDateTime endDate) {
        List<VehicleReservation> vehicleReservationList =
                VehicleReservationRepository.vehicleReservations
                        .stream().filter(vehicleReservation ->
                        vehicleReservation.getUsrId().equalsIgnoreCase(userId) &&
                                ((vehicleReservation.getDueDate() != null &&
                                        startDate.isBefore(vehicleReservation.getDueDate()))
                                        && (vehicleReservation.getFromDate() != null
                                        && endDate.isAfter(vehicleReservation.getFromDate()))))
                        .collect(Collectors.toList());
        return vehicleReservationList.stream()
                .map(vehicleReservation -> VehicleRepository.vehicleMap
                        .get(vehicleReservation.getAllocatedVehicleId()))
                .collect(Collectors.toList());
    }

}
