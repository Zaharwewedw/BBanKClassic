package com.bank.account.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_details", schema = "account")
public class AccountDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "passport_id")
    private long passportId;
    @Column(name = "account_number", unique = true)
    private long accountNumber;
    @Column(name = "bank_details_id", unique = true)
    private long bankDetailsId;
    @Column(name = "money")
    @Digits(integer = 20, fraction = 2)
    private BigDecimal money;
    @Column(name = "negative_balance")
    private boolean negativeBalance;
    @Column(name = "profile_id")
    private long profileId;

    @Override
    public String toString() {
        return "AccountDetails{" +
                "id=" + id +
                ", passportId=" + passportId +
                ", accountNumber=" + accountNumber +
                ", bankDetailsId=" + bankDetailsId +
                ", money=" + money +
                ", negativeBalance=" + negativeBalance +
                ", profileId=" + profileId +
                '}';
    }
}
