package com.lld.carrental.repository;

import com.lld.carrental.model.enums.AccountType;

public class AccountRepositoryFactory {
    public static AccountRepository getAccountRepository(AccountType accountType) {
        switch (accountType) {
            case USER:
                return new UserRepository();
            case ADMIN:
                return new AdminRepository();
            default:
                return null;
        }
    }
}
