package com.bank.transfer.controller;


import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.service.CardTransferService;
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


@WebMvcTest(CardTransferController.class)
public class CardTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardTransferService cardTransferService;

    @Test
    void createTransferTest() throws Exception {
        CardTransferDTO dto = new CardTransferDTO();
        when(cardTransferService.createTransfer(any())).thenReturn(dto);

        mockMvc.perform(post("/api/transfer/cardTransfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"id\": 1,"
                                + "\"accountNumber\": \"23\","
                                + "\"amount\": 5000.00,"
                                + "\"purpose\": \"For credit\","
                                + "\"accountDetailsId\": \"3\""
                                + "}"))
                .andExpect(status().isCreated());

        verify(cardTransferService).createTransfer(any());
    }

    @Test
    public void findById() throws Exception {
        Long id = 1L;
        CardTransferDTO dto = new CardTransferDTO();
        dto.setId(id);
        when(cardTransferService.getById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/transfer/cardTransfer/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        verify(cardTransferService).getById(id);
    }

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(get("/api/transfer/cardTransfer"))
                .andExpect(status().isOk());

        verify(cardTransferService).getAll();
    }

    @Test
    void updateCardTransferTest() throws Exception {
        Long id = 1L;
        CardTransferDTO dto = new CardTransferDTO();
        when(cardTransferService.updateCardTransfer(eq(id), any())).thenReturn(dto);

        mockMvc.perform(patch("/api/transfer/cardTransfer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"id\": 1,"
                                + "\"accountNumber\": \"23\","
                                + "\"amount\": 5000.00,"
                                + "\"purpose\": \"For debit\","
                                + "\"accountDetailsId\": \"3\""
                                + "}"))
                .andExpect(status().isOk());

        verify(cardTransferService).updateCardTransfer(eq(id), any());
    }

    @Test
    void deleteCardTransferTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/transfer/cardTransfer/" + id))
                .andExpect(status().isNoContent());

        verify(cardTransferService).deleteCardTransfer(id);
    }

    @Test
    void findByIdShouldThrowExceptionWhenTransferDoesNotExist() throws Exception {
        Long id = 1L;
        when(cardTransferService.getById(id)).thenReturn(null);

        mockMvc.perform(get("/api/transfer/cardTransfer/" + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchTransferException))
                .andExpect(result -> assertEquals("There is no transfer with this ID",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}