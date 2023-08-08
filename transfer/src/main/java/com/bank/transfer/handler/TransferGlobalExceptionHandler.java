package com.bank.transfer.handler;

import com.bank.transfer.exception.NoSuchTransferException;
import com.bank.transfer.exception.TransferIncorrectData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Класс, который отвечвет за обработку исключеий всех контроллеров
@ControllerAdvice
public class TransferGlobalExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<TransferIncorrectData> historyException(NoSuchTransferException e) {
        TransferIncorrectData exception = new TransferIncorrectData();
        exception.setInfo(e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}


