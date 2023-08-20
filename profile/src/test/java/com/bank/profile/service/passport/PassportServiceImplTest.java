package com.bank.profile.service.passport;

import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.PassportNotFoundException;
import com.bank.profile.repo.PassportRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PassportServiceImplTest {

    @Mock
    private PassportRepo passportRepo;

    @InjectMocks
    private PassportServiceImpl passportService;

    @Test
    void getAll() {
        Passport passport1 = new Passport();
        Passport passport2 = new Passport();

        when(passportRepo.findAll()).thenReturn(List.of(passport1, passport2));

        assertEquals(2, passportService.getAll().size());
    }

    @Test
    void getPassportById() {
        Long id = 1L;
        Passport passport = new Passport();
        passport.setId(1L);

        when(passportRepo.findById(id)).thenReturn(Optional.of(passport));
        assertEquals(1, passportService.getPassportById(id).getId());
    }

    @Test
    void getPassportById_noPassportWithSuchId_throwsPassportNotFoundException() {
        assertThrows(PassportNotFoundException.class, () -> passportService.getPassportById(15L));
    }

    @Test
    void add() {
        Passport passport = new Passport();
        passportService.add(passport);
        verify(passportRepo, Mockito.times(1)).save(passport);
    }

    @Test
    void update() {
        Long id = 1L;
        Passport updatedPassport = new Passport();
        Registration registration = new Registration();

        updatedPassport.setId(id);
        updatedPassport.setRegistration(registration);
        registration.setId(id);

        when(passportRepo.findById(id)).thenReturn(Optional.of(updatedPassport));
        when(passportRepo.save(updatedPassport)).thenReturn(updatedPassport);

        passportService.update(id, updatedPassport);
        verify(passportRepo, Mockito.times(1)).findById(id);
        verify(passportRepo, Mockito.times(1)).save(updatedPassport);
    }

    @Test
    void update_noPassportWithSuchId_throwsPassportNotFoundException() {
        Passport passport = new Passport();
        when(passportRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PassportNotFoundException.class, () -> passportService.update(1L, passport));
    }


    @Test
    void deleteById() {
        Long id = 1L;
        Passport deletedPassport = new Passport();
        when(passportRepo.findById(id)).thenReturn(Optional.of(deletedPassport));

        passportService.deleteById(id);

        verify(passportRepo, Mockito.times(1)).findById(id);
        verify(passportRepo, Mockito.times(1)).deleteById(id);
    }
}