package com.bank.profile.controller;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.service.passport.PassportService;
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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PassportController.class)
class PassportControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PassportService passportService;
    @MockBean
    private PassportMapper passportMapper;

    @Test
    void testGetAllPassport() throws Exception {
        PassportDTO passportDTO1 = new PassportDTO();
        PassportDTO passportDTO2 = new PassportDTO();

        passportDTO1.setIssuedBy("NN");
        passportDTO1.setIssuedBy("LL");

        List<PassportDTO> passportDTOs = List.of(passportDTO1, passportDTO2);

        when(passportService.getAll()).thenReturn(List.of(new Passport(), new Passport()));
        when(passportMapper.listToDTO(anyList())).thenReturn(passportDTOs);

        mockMvc.perform(get("/passport/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].issuedBy").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(passportService, times(1)).getAll();
        verify(passportMapper, times(1)).listToDTO(anyList());
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        PassportDTO passportDTO = new PassportDTO();
        passportDTO.setIssuedBy("NN");

        when(passportService.getPassportById(anyLong())).thenReturn(new Passport());
        when(passportMapper.passportToDTO(any(Passport.class))).thenReturn(passportDTO);

        mockMvc.perform(get("/passport/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.issuedBy").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(passportService, times(1)).getPassportById(anyLong());
        verify(passportMapper, times(1)).passportToDTO(any(Passport.class));
    }

    @Test
    void testAddPassport() throws Exception {
        PassportDTO passportDTO = new PassportDTO();
        passportDTO.setIssuedBy("NN");

        when(passportMapper.DTOToPassport(any(PassportDTO.class))).thenReturn(new Passport());
        when(passportService.add(any(Passport.class))).thenReturn(new Passport());
        when(passportMapper.passportToDTO(any(Passport.class))).thenReturn(passportDTO);

        mockMvc.perform(post("/passport/add")
                        .content(asJsonString(passportDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.issuedBy").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(passportMapper, times(1)).DTOToPassport(any(PassportDTO.class));
        verify(passportService, times(1)).add(any(Passport.class));
        verify(passportMapper, times(1)).passportToDTO(any(Passport.class));
    }

    @Test
    void testUpdatePassport() throws Exception {
        Long id = 1L;
        PassportDTO passportDTO = new PassportDTO();
        passportDTO.setIssuedBy("NN");

        when(passportMapper.DTOToPassport(any(PassportDTO.class))).thenReturn(new Passport());
        when(passportService.update(eq(id), any(Passport.class))).thenReturn(new Passport());
        when(passportMapper.passportToDTO(any(Passport.class))).thenReturn(passportDTO);

        mockMvc.perform(put("/passport/{id}", id)
                        .content(asJsonString(passportDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.issuedBy").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(passportMapper, times(1)).DTOToPassport(any(PassportDTO.class));
        verify(passportService, times(1)).update(eq(id), any(Passport.class));
        verify(passportMapper, times(1)).passportToDTO(any(Passport.class));
    }

    @Test
    void testDeletePassport() throws Exception {
        Long id = 1L;
        PassportDTO passportDTO = new PassportDTO();
        passportDTO.setIssuedBy("NN");

        when(passportService.deleteById(anyLong())).thenReturn(new Passport());
        when(passportMapper.passportToDTO(any(Passport.class))).thenReturn(passportDTO);

        mockMvc.perform(delete("/passport/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.issuedBy").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(passportService, times(1)).deleteById(anyLong());
        verify(passportMapper, times(1)).passportToDTO(any(Passport.class));
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