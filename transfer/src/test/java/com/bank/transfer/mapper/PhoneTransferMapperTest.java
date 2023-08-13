package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTransferMapperTest {

    private final PhoneTransferMapper phoneTransferMapper = Mappers.getMapper(PhoneTransferMapper.class);

    @Test
    public void testMapToEntity() {
        PhoneTransferDTO dto = new PhoneTransferDTO();
        dto.setPurpose("Test for Phone");

        PhoneTransfer entity = phoneTransferMapper.mapToEntity(dto);
        assertEquals(dto.getPurpose(), entity.getPurpose());
    }

    @Test
    public void testMapToDTO() {
        PhoneTransfer entity = new PhoneTransfer();
        entity.setPurpose("Test for Phone");

        PhoneTransferDTO dto = phoneTransferMapper.mapToDTO(entity);
        assertEquals(entity.getPurpose(), dto.getPurpose());
    }
}






