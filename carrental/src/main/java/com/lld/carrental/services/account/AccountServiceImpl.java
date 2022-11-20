package com.lld.carrental.services.account;

import com.lld.carrental.exceptions.AccountDoesNotExistsException;
import com.lld.carrental.model.account.Account;
import com.lld.carrental.model.enums.AccountType;
import com.lld.carrental.repository.AccountRepository;
import com.lld.carrental.repository.AccountRepositoryFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    @Override
    public Account createAccount(Account account, AccountType accountType) {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        return accountRepository.createAccount(account);
    }

    public void resetPassword(String userId, String password,
                              AccountType accountType) throws AccountDoesNotExistsException {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        accountRepository.resetPassword(userId, password);
    }
}
