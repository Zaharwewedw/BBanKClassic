package com.bank.transfer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "card_transfer", schema = "transfer")
public class CardTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number")
    @NotNull(message = "Number may not be null!")
    private Long cardNumber;

    @Column(name = "amount")
    @NotNull(message = "Amount may not be null!")
    private Double amount;

    @Column(name = "purpose")
    @Size(max = 255, message = "Text max 255 characters")
    private String purpose;

    @Column(name = "account_details_id")
    private Long accountDetailsId;
}