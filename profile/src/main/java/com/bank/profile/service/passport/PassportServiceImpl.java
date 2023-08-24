package com.bank.profile.service.passport;

import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.PassportNotFoundException;
import com.bank.profile.repo.PassportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportServiceImpl implements PassportService {

    private final PassportRepo passportRepo;

    @Autowired
    public PassportServiceImpl(PassportRepo passportRepo) {
        this.passportRepo = passportRepo;
    }

    @Override
    public List<Passport> getAll() {
        return passportRepo.findAll();
    }

    @Override
    public Passport getPassportById(Long id) {
        return passportRepo.findById(id)
                .orElseThrow(()-> new PassportNotFoundException(String.format("Passport with id %d not found", id)));
    }

    @Override
    public Passport add(Passport passport) {
        passportRepo.save(passport);
        return passport;
    }

    @Override
    public Passport update(Long id, Passport passport) {
        Passport passportFromDb = passportRepo.findById(id).orElseThrow(()-> new PassportNotFoundException(String.format("Passport with id %d not found", id)));
        Registration registration = passportFromDb.getRegistration();

        passport.setId(passportFromDb.getId());
        passport.getRegistration().setId(registration.getId());

        return passportRepo.save(passport);
    }

    @Override
    public Passport deleteById(Long id) {
        Passport passportById = getPassportById(id);
        passportRepo.deleteById(id);
        return passportById;
    }
}
