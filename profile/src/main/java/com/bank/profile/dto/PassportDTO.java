package com.bank.profile.dto;

import com.bank.profile.entity.Registration;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PassportDTO {

    @NotNull
    private Integer series;
    @NotNull
    private Long number;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String gender;
    @NotNull
    private Date birthDate;
    @NotNull
    private String birthPlace;
    @NotNull
    private String issuedBy;
    @NotNull
    private Date dateOfIssue;
    @NotNull
    private Integer divisionCode;
    private Date expirationDate;
    private Registration registration;
}
