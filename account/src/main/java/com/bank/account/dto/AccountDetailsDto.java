package com.bank.account.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsDto {

    private long id;

    @JsonProperty("passport_id")
    @NotNull
    private long passportId;

    @JsonProperty("account_number")
    @NotNull
    private long accountNumber;

    @JsonProperty("bank_details_id")
    @NotNull
    private long bankDetailsId;

    @NotNull
    private BigDecimal money;

    @JsonProperty("negative_balance")
    @NotNull
    private boolean negativeBalance;

    @JsonProperty("profile_id")
    @NotNull
    private long profileId;
}
