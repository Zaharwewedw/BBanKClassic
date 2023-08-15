package com.bank.antifraud.dto;

import lombok.Data;


@Data
public class AntifraudSuspiciousAccountTransferDTO {

//    private long id;

    private int accountTransferId;

    private boolean isBlocked;

    private boolean isSuspicious;

    private String blockedReason;

    private String suspiciousReason;
}
