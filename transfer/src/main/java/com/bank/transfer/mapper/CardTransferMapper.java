package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

//Интерфейс, который используется для преобразования между DTO и сущностью
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardTransferMapper {
    @Mapping(target = "id",ignore = true)
    CardTransfer mapToEntity(CardTransferDTO dto);
    CardTransferDTO mapToDTO(CardTransfer entity);

}