package com.bank.transfer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//DataTransferObject класс для CardTransfer
@Data
@NoArgsConstructor
public class CardTransferDTO {
    private Long id;
    private Long cardNumber;
    private Double amount;
    private String purpose;
    private Long accountDetailsId;
}
