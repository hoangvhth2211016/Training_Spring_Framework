package com.tasc.training.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class MapperManager {
    
    public ContractMapper contract(){
        return Mappers.getMapper(ContractMapper.class);
    }
    
    public EmployeeMapper employee(){
        return Mappers.getMapper(EmployeeMapper.class);
    }
}
