package com.lld.carrental.model.account;

import com.lld.carrental.model.enums.VehicleType;
import com.lld.carrental.model.reservation.Invoice;
import com.lld.carrental.model.vehicle.Vehicle;
import com.lld.carrental.services.search.VehicleSearchService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class User extends Account {
    private LicenseInfo licenseInfo;
}
