package com.bank.transfer.exception;

//Исключение, которое срабатывает при указании несуществующего id перевода
public class NoSuchTransferException extends RuntimeException {
    public NoSuchTransferException(String message) {
        super(message);
    }
}
