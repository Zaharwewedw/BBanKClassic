package com.bank.account.service;

import com.bank.account.entity.AccountDetailsEntity;

import java.util.List;

public interface AccountDetailsService {

    List<AccountDetailsEntity> getAllAccountDetails();
    AccountDetailsEntity getAccountDetailsById(long id);
    AccountDetailsEntity addAccountDetails(AccountDetailsEntity accountDetailsEntity);
    AccountDetailsEntity updateAccountDetails(AccountDetailsEntity accountDetailsEntity);
    AccountDetailsEntity deleteAccountDetails(long id);


}
