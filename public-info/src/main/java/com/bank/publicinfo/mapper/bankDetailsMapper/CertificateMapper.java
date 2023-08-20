package com.bank.publicinfo.mapper.bankDetailsMapper;

import com.bank.publicinfo.dto.bankDetailsDto.CertificateDto;
import com.bank.publicinfo.entity.bankDetailsEntity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CertificateMapper {
    @Mapping(target = "id", ignore = true)
    Certificate toEntity(CertificateDto certificateDto);

    @Mapping(target = "certificate.bankDetailsId", ignore = true)
    CertificateDto toDto(Certificate certificate);
    @Named("CertificateDtoListToCertificateList")
    List<Certificate> toEntityList(List<CertificateDto> certificateDtoList);

    @Named("CertificateListToCertificateDtoList")
    List<CertificateDto> toDtoList(List<Certificate> licenseList);
}
