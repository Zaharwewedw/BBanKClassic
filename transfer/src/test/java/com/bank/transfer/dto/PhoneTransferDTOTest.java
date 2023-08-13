package com.bank.transfer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PhoneTransferDTOTest {

    private PhoneTransferDTO phoneTransferDTO;

    @BeforeEach
    public void setUp() {
        phoneTransferDTO = new PhoneTransferDTO();
    }

    @Test
    public void testId() {
        phoneTransferDTO.setId(1L);
        assertEquals(1L, phoneTransferDTO.getId());
        assertNotEquals(2L, phoneTransferDTO.getId());
    }

    @Test
    public void testPhoneNumber() {
        phoneTransferDTO.setPhoneNumber(1234567890L);
        assertEquals(1234567890L, phoneTransferDTO.getPhoneNumber());
        assertNotEquals(987654321L, phoneTransferDTO.getPhoneNumber());
    }

    @Test
    public void testAmount() {
        phoneTransferDTO.setAmount(100.50);
        assertEquals(100.50, phoneTransferDTO.getAmount());
        assertNotEquals(150.50, phoneTransferDTO.getAmount());
    }

    @Test
    public void testPurpose() {
        phoneTransferDTO.setPurpose("Top-Up");
        assertEquals("Top-Up", phoneTransferDTO.getPurpose());
        assertNotEquals("Payment", phoneTransferDTO.getPurpose());
    }

    @Test
    public void testAccountDetailsId() {
        phoneTransferDTO.setAccountDetailsId(1L);
        assertEquals(1L, phoneTransferDTO.getAccountDetailsId());
        assertNotEquals(2L, phoneTransferDTO.getAccountDetailsId());
    }

    @Test
    public void testEqualsAndHashCode() {
        PhoneTransferDTO anotherPhoneTransfer = new PhoneTransferDTO();
        assertEquals(phoneTransferDTO, anotherPhoneTransfer);
        assertEquals(phoneTransferDTO.hashCode(), anotherPhoneTransfer.hashCode());

        phoneTransferDTO.setId(1L);
        anotherPhoneTransfer.setId(1L);
        assertEquals(phoneTransferDTO, anotherPhoneTransfer);
        assertEquals(phoneTransferDTO.hashCode(), anotherPhoneTransfer.hashCode());

        phoneTransferDTO.setPhoneNumber(1234567890L);
        assertNotEquals(phoneTransferDTO, anotherPhoneTransfer);
    }

    @Test
    public void testToString() {
        String expectedString = "PhoneTransferDTO(id=null, phoneNumber=null, amount=null, purpose=null, accountDetailsId=null)";
        assertEquals(expectedString, phoneTransferDTO.toString());
    }
}