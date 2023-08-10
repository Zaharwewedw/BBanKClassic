package com.bank.profile.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profile", schema = "profile")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", length = 264, nullable = false)
    private Long phoneNumber;

    @Column(name = "email", length = 370)
    private String email;

    @Column(name = "name_on_card")
    private String nameOnCard;

    @Column(name = "inn", unique = true)
    private Long inn;

    @Column(name = "snils", unique = true)
    private Long snils;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", nullable = false)
    private Passport passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actual_registration_id")
    private ActualRegistration actualRegistration;




}
