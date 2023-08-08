package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDTO;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface PhoneTransferService {
    PhoneTransferDTO createTransfer(PhoneTransferDTO phoneTransferDTO) throws InsufficientResourcesException;

    PhoneTransferDTO getById(Long id);

    List<PhoneTransferDTO> getAll();

    PhoneTransferDTO updatePhoneTransfer(Long id, PhoneTransferDTO phoneTransferDTO);

    void deletePhoneTransfer(Long id);
}
