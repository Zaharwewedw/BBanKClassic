package com.bank.profile.service.profile;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.exception.AccountDetailsNotFoundException;
import com.bank.profile.exception.PassportNotFoundException;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repo.ActualRegistrationRepo;
import com.bank.profile.repo.PassportRepo;
import com.bank.profile.repo.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepo profileRepo;
    private final PassportRepo passportRepo;
    private final ActualRegistrationRepo actualRegRepo;

    @Autowired
    public ProfileServiceImpl(ProfileRepo profileRepo, PassportRepo passportRepo, ActualRegistrationRepo actualRegRepo) {
        this.profileRepo = profileRepo;
        this.passportRepo = passportRepo;
        this.actualRegRepo = actualRegRepo;
    }

    @Override
    public List<Profile> getAll() {
        return profileRepo.findAll();
    }

    @Override
    public Profile getProfileById(Long id) {
        return profileRepo.findById(id).orElseThrow(() -> new ProfileNotFoundException(String.format("Profile with id %d not found", id)));
    }

    @Override
    public Profile add(Profile profile) {
        Long passportId = profile.getPassport().getId();
        Long regId = profile.getActualRegistration().getId();

        Passport passport = passportRepo.findById(passportId).orElseThrow(()-> new PassportNotFoundException(String.format("Passport with id %d not found", passportId)));
        ActualRegistration actualRegistration = actualRegRepo.findById(regId).orElseThrow(()-> new AccountDetailsNotFoundException(String.format("ActualRegistration with id %d not found", regId)));

        profile.setPassport(passport);
        profile.setActualRegistration(actualRegistration);

        profileRepo.save(profile);
        return profile;
    }

    @Override
    public Profile update(Long id, Profile profile) {
        Profile profileFromDb = profileRepo.findById(id).orElseThrow(() -> new ProfileNotFoundException(String.format("Profile with id %d not found", id)));
        profileFromDb.setPhoneNumber(profile.getPhoneNumber());
        profileFromDb.setEmail(profile.getEmail());
        profileFromDb.setNameOnCard(profile.getNameOnCard());
        profileFromDb.setInn(profile.getInn());
        profileFromDb.setSnils(profile.getSnils());

        profileRepo.save(profileFromDb);

        return profileFromDb;
    }

    @Override
    public Profile deleteById(Long id) {
        Profile profileById = getProfileById(id);
        profileRepo.deleteById(id);

        return profileById;
    }
}
