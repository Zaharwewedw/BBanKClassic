package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "suspicious_account_transfers")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AntifraudSuspiciousAccountTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_transfer_id")
    private int accountTransferId;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "is_suspicious")
    private boolean isSuspicious;

    @Column(name = "blocked_reason")
    private String blockedReason;

    @Column(name = "suspicious_reason")
    private String suspiciousReason;

    public AntifraudSuspiciousAccountTransfer(int accountTransferId, boolean isBlocked, boolean isSuspicious, String blockedReason, String suspiciousReason) {
        this.accountTransferId = accountTransferId;
        this.isBlocked = isBlocked;
        this.isSuspicious = isSuspicious;
        this.blockedReason = blockedReason;
        this.suspiciousReason = suspiciousReason;
    }
}
