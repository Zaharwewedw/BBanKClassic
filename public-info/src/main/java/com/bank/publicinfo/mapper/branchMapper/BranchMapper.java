package com.bank.publicinfo.mapper.branchMapper;

import com.bank.publicinfo.dto.branchDto.BranchDto;
import com.bank.publicinfo.entity.branchEntity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AtmMapper.class)
public interface BranchMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "atmList", source = "atmDtoList", qualifiedByName = "AtmDtoListToAtmList")
    Branch toEntity(BranchDto branchDto);

    @Mapping(target = "atmDtoList", source = "atmList", qualifiedByName = "AtmListToAtmDtoList")
    BranchDto toDto(Branch branch);

    @Mapping(target = "atmDtoList", source = "atmList", qualifiedByName = "AtmListToAtmDtoList")
    List<BranchDto> toDtoList(List<Branch> branchList);
}
