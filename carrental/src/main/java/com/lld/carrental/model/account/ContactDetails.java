package com.lld.carrental.model.account;

import com.lld.carrental.model.common.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDetails {
    private String phone;
    private String email;
    private Address address;
    private PersonalInfo personalInfo;
}
