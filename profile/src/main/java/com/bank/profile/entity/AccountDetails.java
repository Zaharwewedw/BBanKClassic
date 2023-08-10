package com.bank.profile.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_details_id", schema = "profile")
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
