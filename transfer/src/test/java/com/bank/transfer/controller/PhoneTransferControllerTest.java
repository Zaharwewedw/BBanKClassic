package com.bank.transfer.controller;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.PhoneTransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(PhoneTransferController.class)
public class PhoneTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneTransferService phoneTransferService;

    @Test
    void createTransferTest() throws Exception {
        PhoneTransferDTO dto = new PhoneTransferDTO();
        when(phoneTransferService.createTransfer(any())).thenReturn(dto);

        mockMvc.perform(post("/api/transfer/phoneTransfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"id\": 1,"
                                + "\"accountNumber\": \"23\","
                                + "\"amount\": 5000.00,"
                                + "\"purpose\": \"For credit\","
                                + "\"accountDetailsId\": \"3\""
                                + "}"))
                .andExpect(status().isCreated());

        verify(phoneTransferService).createTransfer(any());
    }

    @Test
    public void findById() throws Exception {
        Long id = 1L;
        PhoneTransferDTO dto = new PhoneTransferDTO();
        dto.setId(id);
        when(phoneTransferService.getById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/transfer/phoneTransfer/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        verify(phoneTransferService).getById(id);
    }

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(get("/api/transfer/phoneTransfer"))
                .andExpect(status().isOk());

        verify(phoneTransferService).getAll();
    }

    @Test
    void updatePhoneTransferTest() throws Exception {
        Long id = 1L;
        PhoneTransferDTO dto = new PhoneTransferDTO();
        when(phoneTransferService.updatePhoneTransfer(eq(id), any())).thenReturn(dto);

        mockMvc.perform(patch("/api/transfer/phoneTransfer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"id\": 1,"
                                + "\"accountNumber\": \"23\","
                                + "\"amount\": 5000.00,"
                                + "\"purpose\": \"For debit\","
                                + "\"accountDetailsId\": \"3\""
                                + "}"))
                .andExpect(status().isOk());

        verify(phoneTransferService).updatePhoneTransfer(eq(id), any());
    }

    @Test
    void deletePhoneTransferTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/transfer/phoneTransfer/" + id))
                .andExpect(status().isNoContent());

        verify(phoneTransferService).deletePhoneTransfer(id);
    }

    @Test
    void findByIdShouldThrowExceptionWhenTransferDoesNotExist() throws Exception {
        Long id = 1L;
        when(phoneTransferService.getById(id)).thenReturn(null);

        mockMvc.perform(get("/api/transfer/phoneTransfer/" + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchTransferException))
                .andExpect(result -> assertEquals("There is no transfer with this ID",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}