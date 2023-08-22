package com.bank.history.servise;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.History;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.repository.HistoryRepository;
import liquibase.pro.packaged.L;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoryServiseImpTest {
    @Mock
    private HistoryRepository historyRepository;
    @Mock
    private HistoryMapper historyMapper;
    @InjectMocks
    private HistoryServiseImp historyServise;

    @Test
    void getHistoryList() {
        List<History> historyList = Arrays.asList(new History(), new History());
        List<HistoryDto> historyDtoList = Arrays.asList(new HistoryDto(), new HistoryDto());

        when(historyRepository.findAll()).thenReturn(historyList);
        when(historyMapper.toDtoList(historyList)).thenReturn(historyDtoList);

        assertThat(historyServise.getHistoryList()).isEqualTo(historyDtoList);

    }

    @Test
    void getHistoryById() {
        Long id = 1L;
        History history = new History();
        HistoryDto historyDto = new HistoryDto();

        when(historyRepository.findById(id)).thenReturn(Optional.of(history));
        when(historyMapper.toHistoryDto(history)).thenReturn(historyDto);

        historyServise.getHistoryById(id);

        verify(historyRepository).findById(id);
        verify(historyMapper).toHistoryDto(history);

    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        HistoryDto historyDto = new HistoryDto();
        historyDto.setAccount_audit_id(55L);
        History history = new History();
        history.setId(id);
        history.setAccount_audit_id(50L);
        History historyToUpdate = new History();
        historyToUpdate.setId(id);
        historyToUpdate.setAccount_audit_id(historyDto.getAccount_audit_id());


        when(historyRepository.findById(id)).thenReturn(Optional.of(history));
        when(historyMapper.toEntity(historyDto)).thenReturn(historyToUpdate);
        when(historyRepository.save(historyToUpdate)).thenReturn(historyToUpdate);
        when(historyMapper.toHistoryDto(historyToUpdate)).thenReturn(historyDto);


        HistoryDto result = historyServise.update(id, historyDto);


        verify(historyRepository).findById(id);
        verify(historyMapper).toEntity(historyDto);
        verify(historyRepository).save(historyToUpdate);
        verify(historyMapper).toHistoryDto(historyToUpdate);


        assertEquals(historyDto.getAccount_audit_id(), result.getAccount_audit_id());

    }

    @Test
    void delete() {
        Long id = 1L;
        historyServise.delete(id);
        verify(historyRepository).deleteById(id);
    }

    @Test
    void create() {

        HistoryDto historyDto = new HistoryDto();
        History historyToSave = new History();

        when(historyMapper.toEntity(historyDto)).thenReturn(historyToSave);
        when(historyRepository.save(historyToSave)).thenReturn(historyToSave);
        when(historyMapper.toHistoryDto(historyToSave)).thenReturn(historyDto);

        historyServise.create(historyDto);

        verify(historyRepository).save(historyToSave);
        verify(historyMapper).toHistoryDto(historyToSave);
        verify(historyMapper).toEntity(historyDto);

    }


}
