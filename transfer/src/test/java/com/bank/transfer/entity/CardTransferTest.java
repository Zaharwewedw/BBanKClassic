package com.bank.transfer.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CardTransferTest {

    private CardTransfer cardTransfer;
    private Validator validator;

    @BeforeEach
    void setUp() {
        cardTransfer = new CardTransfer();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEqualsAndHashCode() {
        CardTransfer anotherCardTransfer = new CardTransfer();

        assertEquals(cardTransfer, anotherCardTransfer);
        assertEquals(cardTransfer.hashCode(), anotherCardTransfer.hashCode());

        cardTransfer.setId(1L);
        anotherCardTransfer.setId(2L);

        assertNotEquals(cardTransfer, anotherCardTransfer);
    }

    @Test
    void testToString() {
        String expected = "CardTransfer(id=null, cardNumber=null, amount=null, purpose=null, accountDetailsId=null)";
        assertEquals(expected, cardTransfer.toString());
    }

    @Test
    void validationShouldFailForBlankCardNumberAndAmount() {
        Set<ConstraintViolation<CardTransfer>> violations = validator.validate(cardTransfer);

        assertEquals(2, violations.size());
    }

    @Test
    void validationShouldPassForValidCardTransfer() {
        cardTransfer.setCardNumber(1234567890123456L);
        cardTransfer.setAmount(100.50);

        Set<ConstraintViolation<CardTransfer>> violations = validator.validate(cardTransfer);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenPurposeExceedsMaxLength() {
        CardTransfer cardTransfer = new CardTransfer();
        String purposeExceedingMaxLength = "A".repeat(256);
        cardTransfer.setPurpose(purposeExceedingMaxLength);

        Set<ConstraintViolation<CardTransfer>> violations = validator.validate(cardTransfer);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("purpose")));
    }
}