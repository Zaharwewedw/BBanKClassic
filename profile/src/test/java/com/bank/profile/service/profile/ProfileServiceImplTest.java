package com.bank.profile.service.profile;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.exception.ActualRegistrationNotFoundException;
import com.bank.profile.exception.PassportNotFoundException;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repo.ActualRegistrationRepo;
import com.bank.profile.repo.PassportRepo;
import com.bank.profile.repo.ProfileRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ProfileRepo profileRepo;
    @Mock
    private PassportRepo passportRepo;
    @Mock
    private ActualRegistrationRepo actualRegistrationRepo;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Test
    void getAll() {
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();

        when(profileRepo.findAll()).thenReturn(List.of(profile1, profile2));

        assertEquals(2, profileService.getAll().size());
    }

    @Test
    void getProfileById() {
        Long id = 1L;
        Profile profileWithIdOne = new Profile();
        profileWithIdOne.setId(id);

        when(profileRepo.findById(id)).thenReturn(Optional.of(profileWithIdOne));

        assertEquals(1, profileService.getProfileById(id).getId());
    }

    @Test
    void getProfileById_noProfileWithSuchId_throwsProfileNotFoundException() {
        assertThrows(ProfileNotFoundException.class, () -> profileService.getProfileById(15L));
    }

    @Test
    void add() {
        Long id = 1L;
        Profile profile = new Profile();
        ActualRegistration actualRegistration = new ActualRegistration();
        Passport passport = new Passport();

        actualRegistration.setId(id);
        passport.setId(id);

        profile.setActualRegistration(actualRegistration);
        profile.setPassport(passport);

        when(actualRegistrationRepo.findById(id)).thenReturn(Optional.of(actualRegistration));
        when(passportRepo.findById(id)).thenReturn(Optional.of(passport));

        profileService.add(profile);

        Mockito.verify(actualRegistrationRepo, Mockito.times(1)).findById(id);
        Mockito.verify(passportRepo, Mockito.times(1)).findById(id);
    }

    @Test
    void update() {
        Long id = 1L;
        Profile updatedProfile = new Profile();
        updatedProfile.setId(id);
        when(profileRepo.findById(id)).thenReturn(Optional.of(updatedProfile));
        when(profileRepo.save(updatedProfile)).thenReturn(updatedProfile);

        profileService.update(id, updatedProfile);

        Mockito.verify(profileRepo, Mockito.times(1)).findById(id);
        Mockito.verify(profileRepo, Mockito.times(1)).save(updatedProfile);
    }

    @Test
    void update_noProfileWithSuchId_throwsProfileNotFoundException() {
        Profile profile = new Profile();
        when(profileRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProfileNotFoundException.class, () -> profileService.update(1L, profile));
    }

    @Test
    void deleteById() {
        Long id = 1L;
        Profile deletedProfile = new Profile();
        when(profileRepo.findById(id)).thenReturn(Optional.of(deletedProfile));

        profileService.deleteById(id);

        Mockito.verify(profileRepo, Mockito.times(1)).findById(id);
        Mockito.verify(profileRepo, Mockito.times(1)).deleteById(id);
    }

    @Test
    void add_providedPassportNotExists_throwsPassportNotFoundException() {
        Long id = 1L;
        Profile profile = new Profile();
        ActualRegistration actualRegistration = new ActualRegistration();
        Passport passport = new Passport();

        actualRegistration.setId(id);
        passport.setId(id);

        profile.setActualRegistration(actualRegistration);
        profile.setPassport(passport);

        when(passportRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(PassportNotFoundException.class, () -> profileService.add(profile));
    }

    @Test
    void add_providedActualRegistrationNotExists_throwsActualRegistrationNotFoundException() {
        Long id = 1L;
        Profile profile = new Profile();
        ActualRegistration actualRegistration = new ActualRegistration();
        Passport passport = new Passport();

        actualRegistration.setId(id);
        passport.setId(id);

        profile.setActualRegistration(actualRegistration);
        profile.setPassport(passport);

        when(passportRepo.findById(id)).thenReturn(Optional.of(passport));
        when(actualRegistrationRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ActualRegistrationNotFoundException.class, () -> profileService.add(profile));
    }
}