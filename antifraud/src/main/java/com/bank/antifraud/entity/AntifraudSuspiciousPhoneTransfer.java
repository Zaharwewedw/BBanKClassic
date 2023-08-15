package com.bank.antifraud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "suspicious_phone_transfers")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AntifraudSuspiciousPhoneTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phone_transfer_id")
    private int phoneTransferId;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "is_suspicious")
    private boolean isSuspicious;

    @Column(name = "blocked_reason")
    private String blockedReason;

    @Column(name = "suspicious_reason")
    private String suspiciousReason;

    public AntifraudSuspiciousPhoneTransfer(int phoneTransferId, boolean isBlocked, boolean isSuspicious, String blockedReason, String suspiciousReason) {
        this.phoneTransferId = phoneTransferId;
        this.isBlocked = isBlocked;
        this.isSuspicious = isSuspicious;
        this.blockedReason = blockedReason;
        this.suspiciousReason = suspiciousReason;
    }
}
