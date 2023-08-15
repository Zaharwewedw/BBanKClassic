package com.bank.antifraud.util.mapper;

import com.bank.antifraud.dto.AntifraudSuspiciousCardTransferDTO;
import com.bank.antifraud.entity.AntifraudSuspiciousCardTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
    AntifraudSuspiciousCardTransferDTO cardToCardDTO(AntifraudSuspiciousCardTransfer cardTransfer);
    AntifraudSuspiciousCardTransfer cardDTOToCard(AntifraudSuspiciousCardTransferDTO dto);
}
