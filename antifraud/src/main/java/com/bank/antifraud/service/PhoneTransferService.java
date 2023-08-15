package com.bank.antifraud.service;

import com.bank.antifraud.entity.AntifraudSuspiciousPhoneTransfer;

import java.util.List;
import java.util.Optional;

public interface PhoneTransferService {
    
    void save(AntifraudSuspiciousPhoneTransfer s);
    List<AntifraudSuspiciousPhoneTransfer> getAll();

    Optional<AntifraudSuspiciousPhoneTransfer> findById(long id);

    void deleteById(long id);

    AntifraudSuspiciousPhoneTransfer update(long id, AntifraudSuspiciousPhoneTransfer s);
}
