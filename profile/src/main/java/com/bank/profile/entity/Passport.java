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
import java.util.Date;

@Entity
@Table(name = "passport", schema = "profile")
@Data
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "series", nullable = false)
    private Integer series;
    @Column(name = "number", nullable = false)
    private Long number;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "gender", nullable = false, length = 3)
    private String gender;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "birth_place", nullable = false, length = 480)
    private String birthPlace;
    @Column(name = "issued_by", nullable = false, columnDefinition = "text")
    private String issuedBy;
    @Column(name = "date_of_issue", nullable = false)
    private Date dateOfIssue;
    @Column(name = "division_code", nullable = false)
    private Integer divisionCode;
    @Column(name = "expiration_date")
    private Date expirationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id")
    private Registration registration;

}
