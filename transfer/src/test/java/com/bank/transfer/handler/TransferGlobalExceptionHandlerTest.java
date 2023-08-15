package com.bank.transfer.handler;

import com.bank.transfer.controller.AccountTransferController;
import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.exception.TransferIncorrectData;
import com.bank.transfer.service.AccountTransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountTransferController.class)
public class TransferGlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    // Мок сервиса для генерации исключения.
    @MockBean
    private AccountTransferService accountTransferService;

    @Test
    public void testHandleNoSuchTransferException() throws Exception {
        Long testId = 1L;
        String errorMsg = "There is no transfer with this ID";
        when(accountTransferService.getById(testId)).thenThrow(new NoSuchTransferException(errorMsg));

        String expectedJson = new ObjectMapper().writeValueAsString(new TransferIncorrectData(errorMsg));

        mockMvc.perform(get("/api/transfer/accountTransfer/" + testId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedJson));
    }
}