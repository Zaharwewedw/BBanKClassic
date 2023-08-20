package com.bank.profile.controller;

import com.bank.profile.entity.AccountDetails;
import com.bank.profile.service.account.AccountDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountDetailsController.class)
class AccountDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountDetailsService accountDetailsService;

    @Test
    void testGetAllAccountDetails() throws Exception {
        AccountDetails accountDetails1 = new AccountDetails();
        AccountDetails accountDetails2 = new AccountDetails();
        accountDetails1.setId(1L);
        accountDetails2.setId(2L);

        List<AccountDetails> accountDetailsList = List.of(accountDetails1, accountDetails2);

        when(accountDetailsService.getAll()).thenReturn(accountDetailsList);

        mockMvc.perform(get("/account-details/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(accountDetailsService, times(1)).getAll();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(id);
        when(accountDetailsService.getAccountDetailsById(anyLong())).thenReturn(accountDetails);

        mockMvc.perform(get("/account-details/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(accountDetailsService, times(1)).getAccountDetailsById(anyLong());
    }

    @Test
    void testAddAccountDetails() throws Exception {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(1L);

        when(accountDetailsService.add(any(AccountDetails.class))).thenReturn(accountDetails);

        mockMvc.perform(post("/account-details/add")
                        .content(asJsonString(accountDetails))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(accountDetailsService, times(1)).add(any(AccountDetails.class));
    }

    @Test
    void testDeleteAccountDetails() throws Exception {
        Long id = 1L;
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(id);

        when(accountDetailsService.deleteById(anyLong())).thenReturn(accountDetails);

        mockMvc.perform(delete("/account-details/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                // Add more assertions for other properties
                .andDo(MockMvcResultHandlers.print());

        verify(accountDetailsService, times(1)).deleteById(anyLong());
    }

    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}