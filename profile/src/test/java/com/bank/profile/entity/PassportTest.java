package com.bank.profile.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PassportTest {

    @Test
    @DisplayName("Test getters and setters for Passport")
    void testSettersAndGetters() {
        Passport passport = new Passport();

        // Set values using setter methods
        passport.setId(1L);
        passport.setSeries(123);
        passport.setNumber(456789L);
        passport.setLastName("Doe");
        passport.setFirstName("John");
        passport.setMiddleName("Michael");
        passport.setGender("M");
        passport.setBirthDate(new Date());
        passport.setBirthPlace("New York");
        passport.setIssuedBy("Government Authority");
        passport.setDateOfIssue(new Date());
        passport.setDivisionCode(789);
        passport.setExpirationDate(new Date());

        // Use assertAll to group assertions
        assertAll("Passport properties",
                () -> assertEquals(1L, passport.getId()),
                () -> assertEquals(123, passport.getSeries()),
                () -> assertEquals(456789L, passport.getNumber()),
                () -> assertEquals("Doe", passport.getLastName()),
                () -> assertEquals("John", passport.getFirstName()),
                () -> assertEquals("Michael", passport.getMiddleName()),
                () -> assertEquals("M", passport.getGender()),
                // Add more assertions for other properties
                () -> assertEquals("New York", passport.getBirthPlace()),
                () -> assertEquals("Government Authority", passport.getIssuedBy()),
                () -> assertEquals(789, passport.getDivisionCode())
                // Add more assertions for other properties
        );
    }
}