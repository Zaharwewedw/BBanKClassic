package com.bank.transfer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//DataTransferObject класс для PhoneTransfer
@Data
@NoArgsConstructor
public class PhoneTransferDTO {
    private Long id;
    private Long phoneNumber;
    private Double amount;
    private String purpose;
    private Long accountDetailsId;
}