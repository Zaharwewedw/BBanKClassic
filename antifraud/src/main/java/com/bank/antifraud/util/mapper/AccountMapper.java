package com.bank.antifraud.util.mapper;

import com.bank.antifraud.dto.AntifraudSuspiciousAccountTransferDTO;
import com.bank.antifraud.entity.AntifraudSuspiciousAccountTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AntifraudSuspiciousAccountTransferDTO accountToAccountDTO(AntifraudSuspiciousAccountTransfer accountTransfer);
    AntifraudSuspiciousAccountTransfer accountDTOToAccount(AntifraudSuspiciousAccountTransferDTO dto);
}
