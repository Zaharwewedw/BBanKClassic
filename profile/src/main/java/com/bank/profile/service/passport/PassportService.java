package com.bank.profile.service.passport;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;

import java.util.List;

public interface PassportService {

    List<Passport> getAll();
    Passport getPassportById(Long id);

    Passport add(Passport passport);

    Passport update(Long id, Passport passport);

    Passport deleteById(Long id);

}
