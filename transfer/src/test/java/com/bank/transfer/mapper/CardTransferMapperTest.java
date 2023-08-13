package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CardTransferMapperTest {

    private final CardTransferMapper cardTransferMapper = Mappers.getMapper(CardTransferMapper.class);

    @Test
    public void testMapToEntity() {
        CardTransferDTO dto = new CardTransferDTO();
        dto.setPurpose("Test for Card");

        CardTransfer entity = cardTransferMapper.mapToEntity(dto);
        assertEquals(dto.getPurpose(), entity.getPurpose());
    }

    @Test
    public void testMapToDTO() {
        CardTransfer entity = new CardTransfer();
        entity.setPurpose("Test for Card");

        CardTransferDTO dto = cardTransferMapper.mapToDTO(entity);
        assertEquals(entity.getPurpose(), dto.getPurpose());
    }
}