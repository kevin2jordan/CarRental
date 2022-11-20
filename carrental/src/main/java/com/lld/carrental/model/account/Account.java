package com.lld.carrental.model.account;

import com.lld.carrental.model.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Account {
    private String accountId;
    private String userName;
    private String password;
    private AccountStatus accountStatus;
    private ContactDetails contact;
    private String email;
    private LocalDateTime lastAccessed;

    public boolean resetPassword() {

        return true;
    }
}