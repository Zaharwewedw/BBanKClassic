package com.bank.transfer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_number")
    @NotBlank(message = "Number may not be blank!")
    private Long phoneNumber;

    @Column(name = "amount")
    @NotBlank(message = "Amount may not be blank!")
    private Double amount;

    @Column(name = "purpose")
    @Size(max = 255, message = "Text max 255 characters")
    private String purpose;

    @Column(name = "account_details_id")
    private Long accountDetailsId;
}
