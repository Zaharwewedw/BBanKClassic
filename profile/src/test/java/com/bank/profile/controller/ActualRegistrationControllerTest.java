package com.bank.profile.controller;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.service.reg.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActualRegistrationController.class)
class ActualRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService<ActualRegistration> registrationService;

    @Test
    void testGetAllRegistration() throws Exception {
        ActualRegistration registration1 = new ActualRegistration();
        ActualRegistration registration2 = new ActualRegistration();
        registration1.setCountry("Russia");
        registration2.setCountry("Belarus");
        List<ActualRegistration> registrations = List.of(registration1, registration2);

        when(registrationService.getAll()).thenReturn(registrations);

        mockMvc.perform(get("/actual-registration/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].country").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).getAll();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        ActualRegistration registration = new ActualRegistration();
        registration.setId(id);
        when(registrationService.getRegistrationById(anyLong())).thenReturn(registration);

        mockMvc.perform(get("/actual-registration/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).getRegistrationById(anyLong());
    }

    @Test
    void testAddRegistration() throws Exception {
        ActualRegistration registration = new ActualRegistration();
        registration.setId(1L);
        when(registrationService.add(any(ActualRegistration.class))).thenReturn(registration);

        mockMvc.perform(MockMvcRequestBuilders.post("/actual-registration/add")
                        .content(asJsonString(registration))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).add(any(ActualRegistration.class));
    }

    @Test
    void testUpdateRegistration() throws Exception {
        Long id = 1L;
        ActualRegistration registration = new ActualRegistration();
        registration.setId(id);
        when(registrationService.update(eq(id), any(ActualRegistration.class))).thenReturn(registration);

        mockMvc.perform(MockMvcRequestBuilders.put("/actual-registration/{id}", id)
                        .content(asJsonString(registration))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).update(eq(id), any(ActualRegistration.class));
    }

    @Test
    void testDeleteRegistration() throws Exception {
        Long id = 1L;
        ActualRegistration registration = new ActualRegistration();
        registration.setId(id);

        when(registrationService.deleteById(anyLong())).thenReturn(registration);

        mockMvc.perform(MockMvcRequestBuilders.delete("/actual-registration/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).deleteById(anyLong());
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