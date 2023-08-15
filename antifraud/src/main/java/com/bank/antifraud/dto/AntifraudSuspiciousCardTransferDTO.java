package com.bank.antifraud.dto;

import lombok.Data;

@Data
public class AntifraudSuspiciousCardTransferDTO {

//    private long id;

    private int cardTransferId;

    private boolean isBlocked;

    private boolean isSuspicious;

    private String blockedReason;

    private String suspiciousReason;
}
