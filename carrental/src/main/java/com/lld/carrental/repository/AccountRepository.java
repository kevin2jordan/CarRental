package com.lld.carrental.repository;

import com.lld.carrental.exceptions.AccountDoesNotExistsException;
import com.lld.carrental.model.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);

    void resetPassword(String userId, String password) throws AccountDoesNotExistsException;
}
