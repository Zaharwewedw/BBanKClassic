package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDTO;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface AccountTransferService {
    AccountTransferDTO createTransfer(AccountTransferDTO transferDTO) throws InsufficientResourcesException;

    AccountTransferDTO getById(Long id);

    List<AccountTransferDTO> getAll();

    AccountTransferDTO updateAccountTransfer(Long id, AccountTransferDTO accountTransferDTO);

    void deleteAccountTransfer(Long id);
}
