package com.bank.profile.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actual_registration", schema = "profile")
@Data
public class ActualRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", nullable = false, length = 40)
    private String country;
    @Column(name = "region", length = 160)
    private String region;
    @Column(name = "city", length = 160)
    private String city;
    @Column(name = "district", length = 160)
    private String district;
    @Column(name = "locality", length = 230)
    private String locality;
    @Column(name = "street", length = 230)
    private String street;
    @Column(name = "houseNumber", length = 20)
    private String houseNumber;
    @Column(name = "houseBlock", length = 20)
    private String houseBlock;
    @Column(name = "flatNumber", length = 40)
    private String flatNumber;
    @Column(name = "index", nullable = false)
    private Long index;

}
