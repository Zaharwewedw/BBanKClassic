package com.bank.account.service;

import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.repository.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountDetailsImpl implements AccountDetailsService{

    @Autowired
    AccountDetailsRepository repository;

    @Override
    @Transactional
    public List<AccountDetailsEntity> getAllAccountDetails() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public AccountDetailsEntity getAccountDetailsById(long id) {
        return repository.findById(id).orElseThrow(() -> new AccountDetailsNotFoundException(String.format("AccountDetails with id %d not found", id)));
    }

    @Override
    @Transactional
    public AccountDetailsEntity addAccountDetails(AccountDetailsEntity accountDetailsEntity) {
        return repository.saveAndFlush(accountDetailsEntity);
    }

    @Override
    @Transactional
    public AccountDetailsEntity updateAccountDetails(AccountDetailsEntity accountDetailsEntity) {
        repository.findById(accountDetailsEntity.getId()).orElseThrow(() -> new AccountDetailsNotFoundException(String.format("AccountDetails with id %d not found", accountDetailsEntity.getId())));
        return repository.save(accountDetailsEntity);
    }

    @Override
    @Transactional
    public AccountDetailsEntity deleteAccountDetails(long id) {
        AccountDetailsEntity entity = getAccountDetailsById(id);
        repository.deleteById(id);
        return entity;
    }
}
