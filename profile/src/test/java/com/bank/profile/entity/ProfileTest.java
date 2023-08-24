package com.bank.profile.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    @DisplayName("Test getters and setters for Profile")
    void testGettersAndSetters() {
        Profile profile = new Profile();

        Passport passport = new Passport();
        passport.setId(1L); // Set passport ID

        ActualRegistration actualRegistration = new ActualRegistration();
        actualRegistration.setId(2L); // Set actual registration ID

        profile.setPhoneNumber(123456789L);
        profile.setEmail("example@example.com");
        profile.setNameOnCard("John Doe");
        profile.setInn(1234567890L);
        profile.setSnils(9876543210L);
        profile.setPassport(passport);
        profile.setActualRegistration(actualRegistration);

        assertAll("Profile properties",
                () -> assertEquals(123456789L, profile.getPhoneNumber()),
                () -> assertEquals("example@example.com", profile.getEmail()),
                () -> assertEquals("John Doe", profile.getNameOnCard()),
                () -> assertEquals(1234567890L, profile.getInn()),
                () -> assertEquals(9876543210L, profile.getSnils()),
                () -> assertEquals(1L, profile.getPassport().getId()),
                () -> assertEquals(2L, profile.getActualRegistration().getId())
        );
    }
}