package com.bank.profile.service.reg;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.ActualRegistrationNotFoundException;
import com.bank.profile.exception.RegistrationNotFoundException;
import com.bank.profile.repo.ActualRegistrationRepo;
import com.bank.profile.repo.RegistrationRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActualRegistrationServiceImplTest {

    @Mock
    private ActualRegistrationRepo registrationRepo;

    @InjectMocks
    private ActualRegistrationServiceImpl registrationService;

    @Test
    void getAll() {
        ActualRegistration reg1 = new ActualRegistration();
        ActualRegistration reg2 = new ActualRegistration();
        when(registrationRepo.findAll()).thenReturn(List.of(reg1, reg2));

        assertEquals(2, registrationService.getAll().size());
    }

    @Test
    void getRegistrationById() {
        Long id = 1L;
        ActualRegistration regWithIdOne = new ActualRegistration();
        regWithIdOne.setId(id);

        when(registrationRepo.findById(id)).thenReturn(Optional.of(regWithIdOne));

        assertEquals(1, registrationService.getRegistrationById(1L).getId());
    }

    @Test
    void getRegistrationById_noRegistrationWithSuchId_throwsActualRegistrationNotFoundException() {
        assertThrows(ActualRegistrationNotFoundException.class, () -> registrationService.getRegistrationById(15L));
    }

    @Test
    void add() {
        ActualRegistration newRegistration = new ActualRegistration();
        registrationService.add(newRegistration);
        verify(registrationRepo, Mockito.times(1)).save(newRegistration);
    }

    @Test
    void update() {
        Long id = 1L;
        ActualRegistration updatedRegistration = new ActualRegistration();
        updatedRegistration.setId(id);

        when(registrationRepo.findById(id)).thenReturn(Optional.of(updatedRegistration));
        when(registrationRepo.save(updatedRegistration)).thenReturn(updatedRegistration);

        registrationService.update(id, updatedRegistration);

        verify(registrationRepo, Mockito.times(1)).findById(id);
        verify(registrationRepo, Mockito.times(1)).save(updatedRegistration);
    }

    @Test
    void update_noRegistrationWithSuchId_throwsActualRegistrationNotFoundException() {
        ActualRegistration updatedRegistration = new ActualRegistration();
        when(registrationRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ActualRegistrationNotFoundException.class, () -> registrationService.update(1L, updatedRegistration));
    }


    @Test
    void deleteById() {
        Long id = 1L;
        ActualRegistration deletedRegistration = new ActualRegistration();
        when(registrationRepo.findById(id)).thenReturn(Optional.of(deletedRegistration));

        registrationService.deleteById(id);
        verify(registrationRepo, Mockito.times(1)).findById(id);
        verify(registrationRepo, Mockito.times(1)).deleteById(id);
    }
}