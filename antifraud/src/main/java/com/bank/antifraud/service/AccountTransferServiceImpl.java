package com.bank.antifraud.service;

import com.bank.antifraud.entity.AntifraudSuspiciousAccountTransfer;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;
import com.bank.antifraud.repository.AccountTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {

    @Autowired
    public AccountTransferRepository accountTransferRepository;

    public List<AntifraudSuspiciousAccountTransfer> getAll() {
        return accountTransferRepository.findAll();
    }

    @Transactional
    public void save(AntifraudSuspiciousAccountTransfer s) {
        accountTransferRepository.save(s);
    }

    public Optional<AntifraudSuspiciousAccountTransfer> findById(long id) {
        return accountTransferRepository.findById(id);
    }

    @Transactional
    public void deleteById(long id) {
        accountTransferRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AntifraudSuspiciousAccountTransfer update(long id, AntifraudSuspiciousAccountTransfer s) {
        s.setId(id);
        AntifraudSuspiciousAccountTransfer updatedAccountTransfer = accountTransferRepository.save(s);
        return updatedAccountTransfer;
    }


}
