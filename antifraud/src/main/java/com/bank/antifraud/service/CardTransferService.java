package com.bank.antifraud.service;

import com.bank.antifraud.entity.AntifraudSuspiciousAccountTransfer;
import com.bank.antifraud.entity.AntifraudSuspiciousCardTransfer;

import java.util.List;
import java.util.Optional;

public interface CardTransferService {

    void save(AntifraudSuspiciousCardTransfer s);
    List<AntifraudSuspiciousCardTransfer> getAll();

    Optional<AntifraudSuspiciousCardTransfer> findById(long id);

    void deleteById(long id);

    public AntifraudSuspiciousCardTransfer update(long id, AntifraudSuspiciousCardTransfer s);
}
