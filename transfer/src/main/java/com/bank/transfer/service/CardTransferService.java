package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDTO;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface CardTransferService {
    CardTransferDTO createTransfer(CardTransferDTO cardTransferDTO) throws InsufficientResourcesException;

    CardTransferDTO getById(Long id);

    List<CardTransferDTO> getAll();

    CardTransferDTO updateCardTransfer(Long id, CardTransferDTO cardTransferDTO);

    void deleteCardTransfer(Long id);
}
