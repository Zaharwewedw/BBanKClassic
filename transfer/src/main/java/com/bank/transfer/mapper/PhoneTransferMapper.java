package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

//Интерфейс, который используется для преобразования между DTO и сущностью
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneTransferMapper {
    @Mapping(target = "id",ignore = true)
    PhoneTransfer mapToEntity(PhoneTransferDTO dto);
    PhoneTransferDTO mapToDTO(PhoneTransfer entity);

}