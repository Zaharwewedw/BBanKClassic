package com.bank.publicinfo.mapper.bankDetailsMapper;

import com.bank.publicinfo.dto.bankDetailsDto.LicenseDto;
import com.bank.publicinfo.entity.bankDetailsEntity.License;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LicenseMapper {
    @Mapping(target = "id", ignore = true)
    License toEntity(LicenseDto licenseDto);

    @Mapping(target = "license.bankDetailsId", ignore = true)
    LicenseDto toDto(License license);

    @Named("LicenseDtoListToLicenseList")
    List<License> toEntityList(List<LicenseDto> licenseDtoList);

    @Named("LicenseListToLicenseDtoList")
    List<LicenseDto> toDtoList(List<License> licenseList);
}
