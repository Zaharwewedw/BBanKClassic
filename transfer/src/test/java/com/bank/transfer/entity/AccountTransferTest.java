package com.bank.transfer.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountTransferTest {

    private AccountTransfer accountTransfer;
    private Validator validator;

    @BeforeEach
    void setUp() {
        accountTransfer = new AccountTransfer();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEqualsAndHashCode() {
        AccountTransfer anotherAccountTransfer = new AccountTransfer();
        assertEquals(accountTransfer, anotherAccountTransfer);
        assertEquals(accountTransfer.hashCode(), anotherAccountTransfer.hashCode());

        accountTransfer.setId(1L);
        anotherAccountTransfer.setId(2L);

        assertNotEquals(accountTransfer, anotherAccountTransfer);
    }

    @Test
    void testToString() {
        String expected = "AccountTransfer(id=null, accountNumber=null, amount=null, purpose=null, accountDetailsId=null)";
        assertEquals(expected, accountTransfer.toString());
    }

    @Test
    void validationShouldFailForBlankAccountNumberAndAmount() {
        Set<ConstraintViolation<AccountTransfer>> violations = validator.validate(accountTransfer);

        assertEquals(2, violations.size());
    }

    @Test
    void validationShouldPassForValidAccountTransfer() {
        accountTransfer.setAccountNumber(123456789L);
        accountTransfer.setAmount(100.50);

        Set<ConstraintViolation<AccountTransfer>> violations = validator.validate(accountTransfer);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenPurposeExceedsMaxLength() {
        AccountTransfer localAccountTransfer = new AccountTransfer();
        String purposeExceedingMaxLength = "A".repeat(256);
        localAccountTransfer.setPurpose(purposeExceedingMaxLength);

        Set<ConstraintViolation<AccountTransfer>> violations = validator.validate(localAccountTransfer);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("purpose")));
    }
}