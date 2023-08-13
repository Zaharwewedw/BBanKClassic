package com.bank.transfer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CardTransferDTOTest {

    private CardTransferDTO cardTransferDTO;

    @BeforeEach
    public void setUp() {
        cardTransferDTO = new CardTransferDTO();
    }

    @Test
    public void testId() {
        cardTransferDTO.setId(1L);
        assertEquals(1L, cardTransferDTO.getId());
        assertNotEquals(2L, cardTransferDTO.getId());
    }

    @Test
    public void testCardNumber() {
        cardTransferDTO.setCardNumber(1234567890123456L);
        assertEquals(1234567890123456L, cardTransferDTO.getCardNumber());
        assertNotEquals(2345678901234567L, cardTransferDTO.getCardNumber());
    }

    @Test
    public void testAmount() {
        cardTransferDTO.setAmount(100.50);
        assertEquals(100.50, cardTransferDTO.getAmount());
        assertNotEquals(150.50, cardTransferDTO.getAmount());
    }

    @Test
    public void testPurpose() {
        cardTransferDTO.setPurpose("Payment");
        assertEquals("Payment", cardTransferDTO.getPurpose());
        assertNotEquals("Refund", cardTransferDTO.getPurpose());
    }

    @Test
    public void testAccountDetailsId() {
        cardTransferDTO.setAccountDetailsId(1L);
        assertEquals(1L, cardTransferDTO.getAccountDetailsId());
        assertNotEquals(2L, cardTransferDTO.getAccountDetailsId());
    }

    @Test
    public void testEqualsAndHashCode() {
        CardTransferDTO anotherCardTransfer = new CardTransferDTO();
        assertEquals(cardTransferDTO, anotherCardTransfer);
        assertEquals(cardTransferDTO.hashCode(), anotherCardTransfer.hashCode());

        cardTransferDTO.setId(1L);
        anotherCardTransfer.setId(1L);
        assertEquals(cardTransferDTO, anotherCardTransfer);
        assertEquals(cardTransferDTO.hashCode(), anotherCardTransfer.hashCode());

        cardTransferDTO.setCardNumber(1234567890123456L);
        assertNotEquals(cardTransferDTO, anotherCardTransfer);
    }

    @Test
    public void testToString() {
        String expectedString = "CardTransferDTO(id=null, cardNumber=null, amount=null, purpose=null, accountDetailsId=null)";
        assertEquals(expectedString, cardTransferDTO.toString());
    }
}