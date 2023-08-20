package com.bank.publicinfo.mapper.branchMapper;

import com.bank.publicinfo.dto.branchDto.AtmDto;
import com.bank.publicinfo.entity.branchEntity.Atm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AtmMapper {
    @Mapping(target = "id", ignore = true)
    Atm toEntity(AtmDto atmDto);

    @Mapping(target = "atm.branchId", ignore = true)
    AtmDto toDto(Atm atm);

    @Named("AtmDtoListToAtmList")
    List<Atm> toEntityList(List<AtmDto> atmDtoList);

    @Named("AtmListToAtmDtoList")
    List<AtmDto> toDtoList(List<Atm> atmList);
}
