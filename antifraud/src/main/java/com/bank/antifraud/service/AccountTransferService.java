package com.bank.antifraud.service;

import com.bank.antifraud.entity.AntifraudSuspiciousAccountTransfer;
import com.bank.antifraud.exception.AntifraudAccountNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AccountTransferService {
    void save(AntifraudSuspiciousAccountTransfer s);
    List<AntifraudSuspiciousAccountTransfer> getAll();

    Optional<AntifraudSuspiciousAccountTransfer> findById(long id);

    void deleteById(long id);

    AntifraudSuspiciousAccountTransfer update(long id, AntifraudSuspiciousAccountTransfer s);
}
