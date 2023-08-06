package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

//Интерфейс, который используется для преобразования между DTO и сущностью
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountTransferMapper {
    @Mapping(target = "id",ignore = true)
    AccountTransfer mapToEntity(AccountTransferDTO dto);
    AccountTransferDTO mapToDTO(AccountTransfer entity);
}
