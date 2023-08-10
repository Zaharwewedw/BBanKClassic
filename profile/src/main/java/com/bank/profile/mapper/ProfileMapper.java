package com.bank.profile.mapper;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface ProfileMapper {

    ProfileDTO profileToDTO(Profile profile);

    Profile DTOToProfile(ProfileDTO profileDTO);

    List<ProfileDTO> listToDTO(List<Profile> profiles);

    List<Profile> DTOToList(List<ProfileDTO> dtos);
}
