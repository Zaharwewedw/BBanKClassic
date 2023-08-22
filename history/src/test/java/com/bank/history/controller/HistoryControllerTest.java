package com.bank.history.controller;

import com.bank.history.dto.HistoryDto;
import com.bank.history.servise.HistoryServiseImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryController.class)
class HistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HistoryServiseImp historyServiseImp;
    @InjectMocks
    private HistoryController historyController;

    @Test
    void getAllHistoryList() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
        verify(historyServiseImp).getHistoryList();
    }

    @Test
    void getHistoryById() throws Exception {
        Long id = 1L;
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(id);
        when(historyServiseImp.getHistoryById(id)).thenReturn(historyDto);
        mockMvc.perform(get("/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        verify(historyServiseImp).getHistoryById(id);


    }

    @Test
    void createHistory() throws Exception {
        HistoryDto historyDto = new HistoryDto();
        when(historyServiseImp.create(any())).thenReturn(historyDto);
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"id\": 1,"
                        + "\"transfer_audit_id\": \"22\","
                        + "\"profile_audit_id\": \"22\","
                        + "\"account_audit_id\": \"22\","
                        + "\"anti_fraud_audit_id\": \"22\","
                        + "\"public_bank_info_audit_id\": \"22\","
                        + "\"authorization_audit_id\": \"22\""
                        + "}")).andExpect(status().isCreated());
        verify(historyServiseImp).create(any());


    }

    @Test
    void updateHistory() throws Exception {
        Long id = 1L;
        HistoryDto historyDto = new HistoryDto();
        when(historyServiseImp.update(eq(id), any())).thenReturn(historyDto);
        mockMvc.perform(put("/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"id\": 1,"
                        + "\"transfer_audit_id\": \"23\","
                        + "\"profile_audit_id\": \"23\","
                        + "\"account_audit_id\": \"23\","
                        + "\"anti_fraud_audit_id\": \"22\","
                        + "\"public_bank_info_audit_id\": \"22\","
                        + "\"authorization_audit_id\": \"22\""
                        + "}")).andExpect(status().isOk());
        verify(historyServiseImp).update(eq(id), any());
    }

    @Test
    void deleteHistory() throws Exception {
        Long id = 1L;
        ResultActions resultActions = mockMvc.perform(delete("/{id}", id)).andExpect(status().isNoContent());
        verify(historyServiseImp).delete(id);
    }
}
