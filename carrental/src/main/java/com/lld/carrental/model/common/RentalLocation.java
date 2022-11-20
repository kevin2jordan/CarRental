package com.lld.carrental.model.common;

import com.lld.carrental.model.common.Address;
import com.lld.carrental.model.common.Coordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalLocation {
    private String id;
    private Address address;
    private Coordinates coordinates;
}
