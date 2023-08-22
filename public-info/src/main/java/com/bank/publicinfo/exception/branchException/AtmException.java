package com.bank.publicinfo.exception.branchException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AtmException {
    private String message;
    private long timestamp;
}