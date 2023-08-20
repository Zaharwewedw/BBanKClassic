package com.bank.publicinfo.mapper.bankDetailsMapper;

import com.bank.publicinfo.dto.bankDetailsDto.BankDetailsDto;
import com.bank.publicinfo.entity.bankDetailsEntity.BankDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CertificateMapper.class, LicenseMapper.class})
public interface BankDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "licenseList", source = "licenseDtoList", qualifiedByName = "LicenseDtoListToLicenseList")
    @Mapping(target = "certificateList", source = "certificateDtoList", qualifiedByName = "CertificateDtoListToCertificateList")
    BankDetails toEntity(BankDetailsDto bankDetailsDto);

    @Mapping(target = "licenseDtoList", source = "licenseList", qualifiedByName = "LicenseListToLicenseDtoList")
    @Mapping(target = "certificateDtoList", source = "certificateList", qualifiedByName = "CertificateListToCertificateDtoList")
    BankDetailsDto toDto(BankDetails bankDetails);

    @Mapping(target = "licenseDtoList", source = "licenseList", qualifiedByName = "LicenseListToLicenseDtoList")
    @Mapping(target = "certificateDtoList", source = "certificateList", qualifiedByName = "CertificateListToCertificateDtoList")
    List<BankDetailsDto> toDtoList(List<BankDetails> bankDetailsList);
}