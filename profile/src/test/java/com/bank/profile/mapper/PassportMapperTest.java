package com.bank.profile.mapper;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassportMapperTest {

    private final PassportMapper passportMapper = Mappers.getMapper(PassportMapper.class);

    @Test
    void passportToDTO() {
        Passport passport = new Passport();
        passport.setFirstName("Alex");
        PassportDTO passportDTO = passportMapper.passportToDTO(passport);
        assertEquals(passport.getFirstName(), passportDTO.getFirstName());
    }

    @Test
    void DTOToPassport() {
        PassportDTO passportDTO = new PassportDTO();
        passportDTO.setFirstName("Alex");
        Passport passport = passportMapper.DTOToPassport(passportDTO);
        assertEquals(passportDTO.getFirstName(), passport.getFirstName());
    }

    @Test
    void listToDTO() {
        Passport passport1 = new Passport();
        Passport passport2 = new Passport();
        Passport passport3 = new Passport();
        List<PassportDTO> passportDTOS = passportMapper.listToDTO(List.of(passport1, passport2, passport3));
        assertEquals(3, passportDTOS.size());

    }

    @Test
    void DTOToList() {
        PassportDTO passportDTO1 = new PassportDTO();
        PassportDTO passportDTO2 = new PassportDTO();
        PassportDTO passportDTO3 = new PassportDTO();
        List<Passport> passports = passportMapper.DTOToList(List.of(passportDTO1, passportDTO2, passportDTO3));
        assertEquals(3, passports.size());
    }
}