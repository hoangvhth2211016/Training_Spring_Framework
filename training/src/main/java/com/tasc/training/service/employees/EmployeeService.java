package com.tasc.training.service.employees;

import com.tasc.training.model.employee.Employee;
import com.tasc.training.model.employee.EmployeeDto;
import com.tasc.training.model.employee.EmployeeRes;
import com.tasc.training.specification.employee.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {

    Page<Employee> getAll(Pageable pageable);
    
    Employee getById(Long id);
    
    Employee create(EmployeeDto dto);
    
    Employee update(Long id, EmployeeDto dto);
    
    void delete(Long id);
    
    Page<Employee> searchEmployees(SearchCriteria search, Pageable pageable);

    List<Employee> findManagerByHireDate(LocalDate hireDate);
    
    Employee findByContractNr(String contractNr);
}
