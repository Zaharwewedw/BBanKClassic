package com.bank.profile.service.reg;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.exception.ActualRegistrationNotFoundException;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repo.ActualRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualRegistrationServiceImpl implements RegistrationService<ActualRegistration> {

    @Autowired
    private ActualRegistrationRepo repo;

    @Override
    public List<ActualRegistration> getAll() {
        return repo.findAll();
    }

    @Override
    public ActualRegistration getRegistrationById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ActualRegistrationNotFoundException(String.format("ActualRegistration with id %d not found", id)));
    }

    @Override
    public ActualRegistration add(ActualRegistration actualRegistration) {
        return repo.save(actualRegistration);
    }

    @Override
    public ActualRegistration update(Long id, ActualRegistration actualRegistration) {
        ActualRegistration actRegFromDb = repo.findById(id).orElseThrow(() -> new ActualRegistrationNotFoundException(String.format("ActualRegistration with id %d not found", id)));
        actualRegistration.setId(actRegFromDb.getId());

        return repo.save(actualRegistration);
    }

    @Override
    public ActualRegistration deleteById(Long id) {
        ActualRegistration registrationById = getRegistrationById(id);
        repo.deleteById(id);
        return registrationById;
    }
}
