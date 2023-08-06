package com.bank.transfer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//DataTransferObject класс для AccountTransfer
@Data
@NoArgsConstructor
public class AccountTransferDTO {
    private Long id;
    private Long accountNumber;
    private Double amount;
    private String purpose;
    private Long accountDetailsId;
}
