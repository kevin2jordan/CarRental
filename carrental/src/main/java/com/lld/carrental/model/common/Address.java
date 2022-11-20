package com.lld.carrental.model.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
