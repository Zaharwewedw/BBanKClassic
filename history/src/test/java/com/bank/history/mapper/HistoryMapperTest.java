package com.bank.history.mapper;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.History;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryMapperTest {
    private HistoryMapper historyMapper = new HistoryMapperImpl();

    @Test
    void testToEntity() {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(null);
        historyDto.setTransfer_audit_id(null);


        History history = historyMapper.toEntity(historyDto);

        assertEquals(historyDto.getId(), history.getId());
        assertEquals(historyDto.getTransfer_audit_id(), history.getTransfer_audit_id());

    }

    @Test
    void testToHistoryDto() {
        History history = new History();
        history.setId(1L);
        history.setTransfer_audit_id(2L);


        HistoryDto historyDto = historyMapper.toHistoryDto(history);

        assertEquals(history.getId(), historyDto.getId());
        assertEquals(history.getTransfer_audit_id(), historyDto.getTransfer_audit_id());

    }

    @Test
    void testToEntityList() {
        List<HistoryDto> historyDtoList = Arrays.asList(
                new HistoryDto());


        List<History> historyList = historyMapper.toEntityList(historyDtoList);

        assertEquals(historyDtoList.size(), historyList.size());

        for (int i = 0; i < historyDtoList.size(); i++) {
            HistoryDto historyDto = historyDtoList.get(i);
            History history = historyList.get(i);

            assertEquals(historyDto.getId(), history.getId());
            assertEquals(historyDto.getTransfer_audit_id(), history.getTransfer_audit_id());

        }
    }

    @Test
    void testToDtoList() {
        List<History> historyList = Arrays.asList(
                new History(1L, 2L, 3L,
                        4L, 5L, 6L, 7L));


        List<HistoryDto> historyDtoList = historyMapper.toDtoList(historyList);

        assertEquals(historyList.size(), historyDtoList.size());

        for (int i = 0; i < historyList.size(); i++) {
            History history = historyList.get(i);
            HistoryDto historyDto = historyDtoList.get(i);

            assertEquals(history.getId(), historyDto.getId());
            assertEquals(history.getTransfer_audit_id(), historyDto.getTransfer_audit_id());

        }
    }
}

