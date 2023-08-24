package com.bank.profile.mapper;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileMapperTest {

    private final ProfileMapper profileMapper = Mappers.getMapper(ProfileMapper.class);

    @Test
    void profileToDTO() {
        Profile profile = new Profile();
        profile.setInn(555L);
        ProfileDTO profileDTO = profileMapper.profileToDTO(profile);
        assertEquals(profile.getInn(), profileDTO.getInn());
    }

    @Test
    void DTOToProfile() {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setInn(555L);
        Profile profile = profileMapper.DTOToProfile(profileDTO);
        assertEquals(profileDTO.getInn(), profile.getInn());
    }

    @Test
    void listToDTO() {
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        Profile profile3 = new Profile();
        List<ProfileDTO> profileDTOS = profileMapper.listToDTO(List.of(profile1, profile2, profile3));
        assertEquals(3, profileDTOS.size());
    }

    @Test
    void DTOToList() {
        ProfileDTO profileDTO1 = new ProfileDTO();
        ProfileDTO profileDTO2 = new ProfileDTO();
        ProfileDTO profileDTO3 = new ProfileDTO();
        List<Profile> profiles = profileMapper.DTOToList(List.of(profileDTO1, profileDTO2, profileDTO3));
        assertEquals(3, profiles.size());
    }
}