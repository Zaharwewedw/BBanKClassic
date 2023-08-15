package com.bank.antifraud.util.mapper;

import com.bank.antifraud.dto.AntifraudSuspiciousPhoneTransferDTO;
import com.bank.antifraud.entity.AntifraudSuspiciousPhoneTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);
    AntifraudSuspiciousPhoneTransferDTO phoneToPhoneDTO(AntifraudSuspiciousPhoneTransfer phoneTransfer);
    AntifraudSuspiciousPhoneTransfer phoneDTOToPhone(AntifraudSuspiciousPhoneTransferDTO dto);
}
