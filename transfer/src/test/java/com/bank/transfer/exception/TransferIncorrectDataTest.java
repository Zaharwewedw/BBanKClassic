package com.bank.transfer.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransferIncorrectDataTest {

    @Test
    public void equalsShouldReturnTrueForSameObjects() {
        TransferIncorrectData data1 = new TransferIncorrectData("Test Info");
        TransferIncorrectData data2 = new TransferIncorrectData("Test Info");

        assertEquals(data1, data2);
    }

    @Test
    public void equalsShouldReturnFalseForDifferentObjects() {
        TransferIncorrectData data1 = new TransferIncorrectData("Test Info");
        TransferIncorrectData data2 = new TransferIncorrectData("Different Info");

        assertNotEquals(data1, data2);
    }

    @Test
    public void hashCodeShouldBeSameForSameObjects() {
        TransferIncorrectData data1 = new TransferIncorrectData("Test Info");
        TransferIncorrectData data2 = new TransferIncorrectData("Test Info");

        assertEquals(data1.hashCode(), data2.hashCode());
    }

    @Test
    public void hashCodeShouldBeDifferentForDifferentObjects() {
        TransferIncorrectData data1 = new TransferIncorrectData("Test Info");
        TransferIncorrectData data2 = new TransferIncorrectData("Different Info");

        assertNotEquals(data1.hashCode(), data2.hashCode());
    }

    @Test
    public void toStringShouldContainGivenInfo() {
        TransferIncorrectData data = new TransferIncorrectData("Test Info");

        String toStringResult = data.toString();
        assertTrue(toStringResult.contains("Test Info"));
    }
}