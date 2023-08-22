package com.bank.publicinfo.exception.bankDetailsException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CertificateException {
    private String message;
    private long timestamp;
}