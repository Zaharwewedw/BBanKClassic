package com.bank.antifraud.dto;

import lombok.Data;

@Data
public class AntifraudSuspiciousPhoneTransferDTO {

//    private long id;

    private int phoneTransferId;

    private boolean isBlocked;

    private boolean isSuspicious;

    private String blockedReason;

    private String suspiciousReason;
}
