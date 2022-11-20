package com.lld.carrental.services.account;

import com.lld.carrental.exceptions.AccountDoesNotExistsException;
import com.lld.carrental.model.account.Account;
import com.lld.carrental.model.enums.AccountType;

public interface AccountService {
    Account createAccount(Account account, AccountType accountType);

    void resetPassword(String userId, String password,
                       AccountType accountType) throws AccountDoesNotExistsException;
}
