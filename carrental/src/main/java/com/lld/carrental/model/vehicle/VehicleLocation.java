package com.lld.carrental.model.vehicle;

import com.lld.carrental.model.common.Address;
import com.lld.carrental.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleLocation {
    private String locationId;
    private Address address;
    private Coordinates coordinates;
}
