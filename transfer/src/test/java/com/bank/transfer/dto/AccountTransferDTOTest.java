package com.bank.transfer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTransferDTOTest {

    private AccountTransferDTO accountTransferDTO;

    @BeforeEach
    public void setUp() {
        accountTransferDTO = new AccountTransferDTO();
    }

    @Test
    public void testGetSetId() {
        Long id = 1L;
        accountTransferDTO.setId(id);
        assertEquals(id, accountTransferDTO.getId());
    }

    @Test
    public void testGetSetAccountNumber() {
        Long accountNumber = 1234567890L;
        accountTransferDTO.setAccountNumber(accountNumber);
        assertEquals(accountNumber, accountTransferDTO.getAccountNumber());
    }

    @Test
    public void testGetSetAmount() {
        Double amount = 500.25;
        accountTransferDTO.setAmount(amount);
        assertEquals(amount, accountTransferDTO.getAmount());
    }

    @Test
    public void testGetSetPurpose() {
        String purpose = "purpose";
        accountTransferDTO.setPurpose(purpose);
        assertEquals(purpose, accountTransferDTO.getPurpose());
    }

    @Test
    public void testGetSetAccountDetailsId() {
        Long accountDetailsId = 5L;
        accountTransferDTO.setAccountDetailsId(accountDetailsId);
        assertEquals(accountDetailsId, accountTransferDTO.getAccountDetailsId());
    }

    @Test
    public void testEqualsAndHashCode() {
        AccountTransferDTO anotherDTO = new AccountTransferDTO();
        assertEquals(accountTransferDTO, anotherDTO);
        assertEquals(accountTransferDTO.hashCode(), anotherDTO.hashCode());

        anotherDTO.setId(1L);
        assertNotEquals(accountTransferDTO, anotherDTO);
        assertNotEquals(accountTransferDTO.hashCode(), anotherDTO.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "AccountTransferDTO(id=null, accountNumber=null, amount=null, purpose=null, accountDetailsId=null)";
        assertEquals(expected, accountTransferDTO.toString());
    }
}