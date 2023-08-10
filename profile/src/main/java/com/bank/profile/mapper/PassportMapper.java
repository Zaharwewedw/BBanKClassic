package com.bank.profile.mapper;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface PassportMapper {

    PassportDTO passportToDTO(Passport passport);

    Passport DTOToPassport(PassportDTO passportDTO);

    List<PassportDTO> listToDTO(List<Passport> passports);

    List<Passport> DTOToList(List<PassportDTO> dtos);

}
