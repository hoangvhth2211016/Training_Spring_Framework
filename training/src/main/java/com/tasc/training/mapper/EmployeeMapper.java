package com.tasc.training.mapper;

import com.tasc.training.model.employee.Employee;
import com.tasc.training.model.employee.EmployeeDto;
import com.tasc.training.model.employee.EmployeeRes;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    Employee fromId(Long id);
 
    @Mapping(target = "manager", source = "managerId")
    Employee toEntity(EmployeeDto employeeDto);

    
    EmployeeRes toRes(Employee employee);
    
    EmployeeDto toDto(Employee employee);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(EmployeeDto employeeDto, @MappingTarget Employee employee);

}