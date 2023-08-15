package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AntifraudSuspiciousPhoneTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneTransferRepository extends JpaRepository<AntifraudSuspiciousPhoneTransfer, Long> {
}
