package com.bank.publicinfo.dto.branchDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.time.OffsetTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AtmDto {
    Long id;
    @Size(max = 370, message = "Адрес должен быть не более 370 символов длиной")
    String address;
    OffsetTime startOfWork;
    OffsetTime endOfWork;
    Boolean allHours;
}
