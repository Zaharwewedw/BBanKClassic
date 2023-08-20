package com.bank.publicinfo.dto.branchDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.time.OffsetTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class BranchDto {
    Long id;
    @Size(max = 370, message = "Адрес должен быть не более 370 символов длиной")
    String address;
    Long phoneNumber;
    @Size(max = 250, message = "Название города должно быть не более 250 символов длиной")
    String city;
    OffsetTime startOfWork;
    OffsetTime endOfWork;
    List<AtmDto> atmDtoList;
}
