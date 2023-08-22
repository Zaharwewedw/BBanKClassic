package com.bank.publicinfo.service.bankDetailsService;

import com.bank.publicinfo.entity.bankDetailsEntity.BankDetails;

import java.util.List;

public interface BankDetailsService {
    List<BankDetails> getAll();

    BankDetails getById(Long id);

    BankDetails save(BankDetails bankDetails);

    BankDetails update(Long id, BankDetails newBankDetails);

    BankDetails delete(Long id);
}
