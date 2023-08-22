package com.bank.publicinfo.repository.bankDetailsRepository;

import com.bank.publicinfo.entity.bankDetailsEntity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
}
