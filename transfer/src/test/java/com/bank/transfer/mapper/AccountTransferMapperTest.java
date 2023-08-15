package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class AccountTransferMapperTest {

    private final AccountTransferMapper accountTransferMapper = Mappers.getMapper(AccountTransferMapper.class);

    @Test
    public void testMapToEntity() {
        AccountTransferDTO dto = new AccountTransferDTO();
        dto.setPurpose("Test for Account");

        AccountTransfer entity = accountTransferMapper.mapToEntity(dto);
        assertEquals(dto.getPurpose(), entity.getPurpose());
    }

    @Test
    public void testMapToDTO() {
        AccountTransfer entity = new AccountTransfer();
        entity.setPurpose("Test for Account");

        AccountTransferDTO dto = accountTransferMapper.mapToDTO(entity);
        assertEquals(entity.getPurpose(), dto.getPurpose());
    }
}