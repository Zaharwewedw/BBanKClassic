package com.bank.profile.controller;

import com.bank.profile.entity.Registration;
import com.bank.profile.service.reg.RegistrationService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService<Registration> registrationService;


    @Test
    public void testGetAllRegistration() throws Exception {
        Registration registration1 = new Registration();
        Registration registration2 = new Registration();
        Registration registration3 = new Registration();

        when(registrationService.getAll()).thenReturn(List.of(registration1, registration2, registration3));
        mockMvc
                .perform(get("/registration/all"))
                .andExpect(status().isOk());
        verify(registrationService, times(1)).getAll();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        Registration registration = new Registration();
        registration.setId(id);

        when(registrationService.getRegistrationById(id)).thenReturn(registration);

        mockMvc.perform(get("/registration/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).getRegistrationById(anyLong());
    }

    @Test
    void testAddRegistration() throws Exception {
        Registration registration = new Registration();
        registration.setId(1L);

        when(registrationService.add(any(Registration.class))).thenReturn(registration);

        mockMvc.perform(post("/registration/add")
                        .content(asJsonString(registration))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).add(any(Registration.class));
    }

    @Test
    void testUpdateRegistration() throws Exception {
        Long id = 1L;
        Registration registration = new Registration();
        registration.setId(id);

        when(registrationService.update(eq(id), any(Registration.class))).thenReturn(registration);

        mockMvc.perform(put("/registration/{id}", id)
                        .content(asJsonString(registration))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andDo(MockMvcResultHandlers.print());

        verify(registrationService, times(1)).update(eq(id), any(Registration.class));
    }

    @Test
    void testDeleteRegistration() throws Exception {
        Long id = 1L;
        Registration registration = new Registration();
        registration.setId(id);

        when(registrationService.deleteById(anyLong())).thenReturn(registration);

        mockMvc.perform(delete("/registration/{id}", id))
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