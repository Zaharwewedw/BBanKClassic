package com.bank.publicinfo.handler;

import com.bank.publicinfo.exception.bankDetailsException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankDetailsExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<BankDetailsException> bankDetailsException(BankDetailsNotFoundException e) {
        BankDetailsException exception = new BankDetailsException("BankDetails not found", System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BankDetailsException> bankDetailsException(BankDetailsNotSaveException e) {
        BankDetailsException exception = new BankDetailsException(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<LicenseException> licenseException(LicenseNotFoundException e) {
        LicenseException exception = new LicenseException("License not found", System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<LicenseException> bankDetailsException(LicenseNotSaveException e) {
        LicenseException exception = new LicenseException(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<CertificateException> licenseException(CertificateNotFoundException e) {
        CertificateException exception = new CertificateException("Certificate not found", System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<CertificateException> bankDetailsException(CertificateNotSaveException e) {
        CertificateException exception = new CertificateException(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }




//    @ExceptionHandler(ConstraintViolationException.class)
//    private ResponseEntity<BankDetailsException> bankDetailsException(ConstraintViolationException e) {
//        BankDetailsException exception = new BankDetailsException(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
//    }
}
