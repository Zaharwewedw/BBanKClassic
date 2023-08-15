package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AntifraudSuspiciousAccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransferRepository extends JpaRepository<AntifraudSuspiciousAccountTransfer, Long> {
}
