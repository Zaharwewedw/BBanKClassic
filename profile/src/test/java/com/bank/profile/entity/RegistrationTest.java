package com.bank.profile.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {
    @Test
    @DisplayName("Test getters and setters for Registration")
    void testGettersAndSetters() {
        Registration registration = new Registration();
        registration.setCountry("Country");
        registration.setRegion("Region");
        registration.setCity("City");
        registration.setDistrict("District");
        registration.setLocality("Locality");
        registration.setStreet("Street");
        registration.setHouseNumber("123");
        registration.setHouseBlock("A");
        registration.setFlatNumber("Flat 101");
        registration.setIndex(12345L);

        assertAll("Registration properties",
                () -> assertEquals("Country", registration.getCountry()),
                () -> assertEquals("Region", registration.getRegion()),
                () -> assertEquals("City", registration.getCity()),
                () -> assertEquals("District", registration.getDistrict()),
                () -> assertEquals("Locality", registration.getLocality()),
                () -> assertEquals("Street", registration.getStreet()),
                () -> assertEquals("123", registration.getHouseNumber()),
                () -> assertEquals("A", registration.getHouseBlock()),
                () -> assertEquals("Flat 101", registration.getFlatNumber()),
                () -> assertEquals(12345L, registration.getIndex())
        );
    }
}