package com.bank.publicinfo.exception.branchException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BranchException {
    private String message;
    private long timestamp;
}
