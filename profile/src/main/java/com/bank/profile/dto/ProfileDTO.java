package com.bank.profile.dto;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProfileDTO {

    @NotNull
    private Long phoneNumber;

    private String email;

    private String nameOnCard;

    private Long inn;

    private Long snils;

    @NotNull
    private Passport passport;

    private ActualRegistration actualRegistration;
}
