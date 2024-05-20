package com.tasc.training.mapper;

import com.tasc.training.model.contract.Contract;
import com.tasc.training.model.contract.ContractDto;
import com.tasc.training.model.contract.ContractRes;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = EmployeeMapper.class)
public interface ContractMapper {
    
    @Mapping(target = "employee", source = "employeeId")
    Contract toEntity(ContractDto dto);
    
    ContractRes toRes(Contract contract);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contract partialUpdate(ContractDto contractDto, @MappingTarget Contract contract);
}