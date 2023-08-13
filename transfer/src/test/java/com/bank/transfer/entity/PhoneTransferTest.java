package com.bank.transfer.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTransferTest {

    private PhoneTransfer phoneTransfer;
    private Validator validator;

    @BeforeEach
    void setUp() {
        phoneTransfer = new PhoneTransfer();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEqualsAndHashCode() {
        PhoneTransfer anotherPhoneTransfer = new PhoneTransfer();

        assertEquals(phoneTransfer, anotherPhoneTransfer);
        assertEquals(phoneTransfer.hashCode(), anotherPhoneTransfer.hashCode());

        phoneTransfer.setId(1L);
        anotherPhoneTransfer.setId(2L);

        assertNotEquals(phoneTransfer, anotherPhoneTransfer);
    }

    @Test
    void testToString() {
        String expected = "PhoneTransfer(id=null, phoneNumber=null, amount=null, purpose=null, accountDetailsId=null)";
        assertEquals(expected, phoneTransfer.toString());
    }

    @Test
    void validationShouldFailForBlankPhoneNumberAndAmount() {
        Set<ConstraintViolation<PhoneTransfer>> violations = validator.validate(phoneTransfer);

        assertEquals(2, violations.size());
    }

    @Test
    void validationShouldPassForValidPhoneTransfer() {
        phoneTransfer.setPhoneNumber(1234567890L);
        phoneTransfer.setAmount(100.50);

        Set<ConstraintViolation<PhoneTransfer>> violations = validator.validate(phoneTransfer);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenPurposeExceedsMaxLength() {
        PhoneTransfer phoneTransfer = new PhoneTransfer();
        String purposeExceedingMaxLength = "A".repeat(256);
        phoneTransfer.setPurpose(purposeExceedingMaxLength);

        Set<ConstraintViolation<PhoneTransfer>> violations = validator.validate(phoneTransfer);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("purpose")));
    }
}