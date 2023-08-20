package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.service.profile.ProfileService;
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

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;
    @MockBean
    private ProfileMapper profileMapper;

    @Test
    void testGetAllProfile() throws Exception {
        ProfileDTO profileDTO1 = new ProfileDTO();
        ProfileDTO profileDTO2 = new ProfileDTO();
        profileDTO1.setInn(666L);
        profileDTO2.setInn(555L);

        List<ProfileDTO> profileDTOs = List.of(profileDTO1, profileDTO2);

        when(profileService.getAll()).thenReturn(List.of(new Profile(), new Profile()));
        when(profileMapper.listToDTO(anyList())).thenReturn(profileDTOs);

        mockMvc.perform(get("/profile/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].inn").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(profileService, times(1)).getAll();
        verify(profileMapper, times(1)).listToDTO(anyList());
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setInn(55L);

        when(profileService.getProfileById(anyLong())).thenReturn(new Profile());
        when(profileMapper.profileToDTO(any(Profile.class))).thenReturn(profileDTO);

        mockMvc.perform(get("/profile/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inn").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(profileService, times(1)).getProfileById(anyLong());
        verify(profileMapper, times(1)).profileToDTO(any(Profile.class));
    }

    @Test
    void testAddProfile() throws Exception {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setInn(555L);

        when(profileMapper.DTOToProfile(any(ProfileDTO.class))).thenReturn(new Profile());
        when(profileService.add(any(Profile.class))).thenReturn(new Profile());
        when(profileMapper.profileToDTO(any(Profile.class))).thenReturn(profileDTO);

        mockMvc.perform(post("/profile/add")
                        .content(asJsonString(profileDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inn").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(profileMapper, times(1)).DTOToProfile(any(ProfileDTO.class));
        verify(profileService, times(1)).add(any(Profile.class));
        verify(profileMapper, times(1)).profileToDTO(any(Profile.class));
    }

    @Test
    void testUpdateProfile() throws Exception {
        Long id = 1L;
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setInn(555L);

        when(profileMapper.DTOToProfile(any(ProfileDTO.class))).thenReturn(new Profile());
        when(profileService.update(eq(id), any(Profile.class))).thenReturn(new Profile());
        when(profileMapper.profileToDTO(any(Profile.class))).thenReturn(profileDTO);

        mockMvc.perform(put("/profile/{id}", id)
                        .content(asJsonString(profileDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inn").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(profileMapper, times(1)).DTOToProfile(any(ProfileDTO.class));
        verify(profileService, times(1)).update(eq(id), any(Profile.class));
        verify(profileMapper, times(1)).profileToDTO(any(Profile.class));
    }

    @Test
    void testDeleteProfile() throws Exception {
        Long id = 1L;
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setInn(555L);

        when(profileService.deleteById(anyLong())).thenReturn(new Profile());
        when(profileMapper.profileToDTO(any(Profile.class))).thenReturn(profileDTO);

        mockMvc.perform(delete("/profile/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inn").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(profileService, times(1)).deleteById(anyLong());
        verify(profileMapper, times(1)).profileToDTO(any(Profile.class));
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