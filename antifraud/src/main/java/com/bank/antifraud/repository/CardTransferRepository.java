package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AntifraudSuspiciousCardTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTransferRepository extends JpaRepository<AntifraudSuspiciousCardTransfer, Long> {
}
