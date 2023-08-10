package com.bank.profile.service.account;

import com.bank.profile.entity.AccountDetails;

import java.util.List;

public interface AccountDetailsService {

    List<AccountDetails> getAll();
    AccountDetails getAccountDetailsById(Long id);

    AccountDetails add(AccountDetails accountDetails);

    AccountDetails deleteById(Long id);

}
