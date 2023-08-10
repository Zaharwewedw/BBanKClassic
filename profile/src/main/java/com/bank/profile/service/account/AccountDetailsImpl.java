package com.bank.profile.service.account;

import com.bank.profile.entity.AccountDetails;
import com.bank.profile.exception.AccountDetailsNotFoundException;
import com.bank.profile.repo.AccountDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDetailsImpl implements AccountDetailsService {

    @Autowired
    private AccountDetailsRepo accountDetailsRepo;
    @Override
    public List<AccountDetails> getAll() {
        return accountDetailsRepo.findAll();
    }

    @Override
    public AccountDetails getAccountDetailsById(Long id) {
        return accountDetailsRepo.findById(id).orElseThrow(
                () -> new AccountDetailsNotFoundException(String.format("Account with id: %d not found.", id)));
    }

    @Override
    public AccountDetails add(AccountDetails accountDetails) {
        return accountDetailsRepo.save(accountDetails);
    }

    @Override
    public AccountDetails deleteById(Long id) {
        AccountDetails accountDetailsById = getAccountDetailsById(id);
        accountDetailsRepo.deleteById(id);
        return accountDetailsById;
    }
}
