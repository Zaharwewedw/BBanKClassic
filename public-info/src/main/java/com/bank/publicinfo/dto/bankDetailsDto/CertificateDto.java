package com.bank.publicinfo.dto.bankDetailsDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CertificateDto {
    Long id;
    @NotNull
    byte[] photo;
}
