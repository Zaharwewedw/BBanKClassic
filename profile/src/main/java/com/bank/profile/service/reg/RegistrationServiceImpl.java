package com.bank.profile.service.reg;

import com.bank.profile.entity.Registration;
import com.bank.profile.exception.RegistrationNotFoundException;
import com.bank.profile.repo.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService<Registration> {

    @Autowired
    private RegistrationRepo repo;

    @Override
    public List<Registration> getAll() {
        return repo.findAll();
    }

    @Override
    public Registration getRegistrationById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RegistrationNotFoundException(String.format("Registration with id %d not found", id)));
    }

    @Override
    public Registration add(Registration registration) {
        return repo.save(registration);
    }

    @Override
    public Registration update(Long id, Registration registration) {
        repo.findById(id).orElseThrow(() -> new RegistrationNotFoundException(String.format("Registration with id %d not found", id)));
        registration.setId(id);
        return repo.save(registration);
    }

    @Override
    public Registration deleteById(Long id) {
        Registration registrationById = getRegistrationById(id);
        repo.deleteById(id);
        return registrationById;
    }
}
