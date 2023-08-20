package com.bank.publicinfo.dto.bankDetailsDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class BankDetailsDto {
    Long id;
    @NotNull
    Long bik;
    @NotNull
    Long inn;
    @NotNull
    Long kpp;
    @NotNull
    Integer corAccount;
    @Size(max = 180, message = "Название города должно быть не более 180 символов длиной")
    String city;
    @Size(max = 15, message = "Название организации должно быть не более 15 символов длиной")
    String jointStockCompany;
    @Size(max = 80, message = "Имя должно быть не более 80 символов длиной")
    String name;
    List<LicenseDto> licenseDtoList;
    List<CertificateDto> certificateDtoList;
}