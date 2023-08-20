package com.bank.profile.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActualRegistrationTest {

    @Test
    @DisplayName("Test getters and setters for ActualRegistration")
    void testGetterAndSetterMethods() {
        // Create a mock object of the entity
        ActualRegistration actualRegistration = new ActualRegistration();

        // Set values using setter methods
        actualRegistration.setCountry("Country");
        actualRegistration.setRegion("Region");
        actualRegistration.setCity("City");
        actualRegistration.setDistrict("District");
        actualRegistration.setLocality("Locality");
        actualRegistration.setStreet("Street");
        actualRegistration.setHouseNumber("123");
        actualRegistration.setHouseBlock("A");
        actualRegistration.setFlatNumber("Flat 101");
        actualRegistration.setIndex(12345L);

        assertAll("ActualRegistration properties",
                () -> assertEquals("Country", actualRegistration.getCountry()),
                () -> assertEquals("Region", actualRegistration.getRegion()),
                () -> assertEquals("City", actualRegistration.getCity()),
                () -> assertEquals("District", actualRegistration.getDistrict()),
                () -> assertEquals("Locality", actualRegistration.getLocality()),
                () -> assertEquals("Street", actualRegistration.getStreet()),
                () -> assertEquals("123", actualRegistration.getHouseNumber()),
                () -> assertEquals("A", actualRegistration.getHouseBlock()),
                () -> assertEquals("Flat 101", actualRegistration.getFlatNumber()),
                () -> assertEquals(12345L, actualRegistration.getIndex())
        );
    }
}