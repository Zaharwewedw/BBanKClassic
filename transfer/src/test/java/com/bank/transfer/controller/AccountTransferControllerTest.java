package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.AccountTransferService;
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

@WebMvcTest(AccountTransferController.class)
public class AccountTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountTransferService accountTransferService;

    @Test
    void createTransferTest() throws Exception {
        AccountTransferDTO dto = new AccountTransferDTO();
        when(accountTransferService.createTransfer(any())).thenReturn(dto);

        mockMvc.perform(post("/api/transfer/accountTransfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"id\": 1,"
                                + "\"accountNumber\": \"23\","
                                + "\"amount\": 5000.00,"
                                + "\"purpose\": \"For credit\","
                                + "\"accountDetailsId\": \"3\""
                                + "}"))
                .andExpect(status().isCreated());

        verify(accountTransferService).createTransfer(any());
    }

    @Test
    public void findById() throws Exception {
        Long id = 1L;
        AccountTransferDTO dto = new AccountTransferDTO();
        dto.setId(id);
        when(accountTransferService.getById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/transfer/accountTransfer/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        verify(accountTransferService).getById(id);
    }

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(get("/api/transfer/accountTransfer"))
                .andExpect(status().isOk());

        verify(accountTransferService).getAll();
    }

    @Test
    void updateAccountTransferTest() throws Exception {
        Long id = 1L;
        AccountTransferDTO dto = new AccountTransferDTO();
        when(accountTransferService.updateAccountTransfer(eq(id), any())).thenReturn(dto);

        mockMvc.perform(patch("/api/transfer/accountTransfer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"id\": 1,"
                                + "\"accountNumber\": \"23\","
                                + "\"amount\": 5000.00,"
                                + "\"purpose\": \"For debit\","
                                + "\"accountDetailsId\": \"3\""
                                + "}"))
                .andExpect(status().isOk());

        verify(accountTransferService).updateAccountTransfer(eq(id), any());
    }

    @Test
    void deleteAccountTransferTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/transfer/accountTransfer/" + id))
                .andExpect(status().isNoContent());

        verify(accountTransferService).deleteAccountTransfer(id);
    }

    @Test
    void findByIdShouldThrowExceptionWhenTransferDoesNotExist() throws Exception {
        Long id = 1L;
        when(accountTransferService.getById(id)).thenReturn(null);

        mockMvc.perform(get("/api/transfer/accountTransfer/" + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchTransferException))
                .andExpect(result -> assertEquals("There is no transfer with this ID",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}