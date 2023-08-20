package com.bank.profile.service.reg;

import com.bank.profile.entity.Registration;
import com.bank.profile.exception.RegistrationNotFoundException;
import com.bank.profile.repo.RegistrationRepo;
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
class RegistrationServiceImplTest {

    @Mock
    private RegistrationRepo registrationRepo;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    void getAll() {
        Registration reg1 = new Registration();
        Registration reg2 = new Registration();
        when(registrationRepo.findAll()).thenReturn(List.of(reg1, reg2));

        assertEquals(2, registrationService.getAll().size());
    }

    @Test
    void getRegistrationById() {
        Long id = 1L;
        Registration regWithIdOne = new Registration();
        regWithIdOne.setId(id);

        when(registrationRepo.findById(id)).thenReturn(Optional.of(regWithIdOne));

        assertEquals(1, registrationService.getRegistrationById(1L).getId());
    }

    @Test
    void getRegistrationById_noRegistrationWithSuchId_throwsRegistrationNotFoundException() {
        assertThrows(RegistrationNotFoundException.class, () -> registrationService.getRegistrationById(15L));
    }

    @Test
    void add() {
        Registration newRegistration = new Registration();
        registrationService.add(newRegistration);
        verify(registrationRepo, Mockito.times(1)).save(newRegistration);
    }

    @Test
    void update() {
        Long id = 1L;
        Registration updatedRegistration = new Registration();
        updatedRegistration.setId(id);

        when(registrationRepo.save(updatedRegistration)).thenReturn(updatedRegistration);
        when(registrationRepo.findById(id)).thenReturn(Optional.of(updatedRegistration));

        registrationService.update(id, updatedRegistration);

        verify(registrationRepo, Mockito.times(1)).findById(id);
        verify(registrationRepo, Mockito.times(1)).save(updatedRegistration);
    }

    @Test
    void update_noRegistrationWithSuchId_throwsRegistrationNotFoundException() {
        Registration updatedRegistration = new Registration();
        when(registrationRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RegistrationNotFoundException.class, () -> registrationService.update(1L, updatedRegistration));
    }


    @Test
    void deleteById() {
        Long id = 1L;
        Registration deletedRegistration = new Registration();
        when(registrationRepo.findById(1L)).thenReturn(Optional.of(deletedRegistration));

        registrationService.deleteById(1L);
        verify(registrationRepo, Mockito.times(1)).findById(id);
        verify(registrationRepo, Mockito.times(1)).deleteById(id);
    }
}