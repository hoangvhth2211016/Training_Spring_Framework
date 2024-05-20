package com.tasc.training.service.employees;

import com.tasc.training.exception.NotFoundException;
import com.tasc.training.mapper.MapperManager;
import com.tasc.training.model.contract.Contract;
import com.tasc.training.model.employee.Employee;
import com.tasc.training.model.employee.EmployeeDto;
import com.tasc.training.model.employee.EmployeeRes;
import com.tasc.training.repository.EmployeeRepo;
import com.tasc.training.specification.employee.EmployeeSpecification;
import com.tasc.training.specification.employee.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private MapperManager mapper;
    
    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepo.findAll(pageable);
    }
    
    @Override
    public Employee getById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }
    
    @Override
    public Employee create(EmployeeDto dto) {
        Employee emp = mapper.employee().toEntity(dto);
        if(dto.getContractDto() != null){
            Contract newContract = dto.getContractDto().toEntity();
            newContract.setEmployee(emp);
            emp.getContracts().add(newContract);
            emp.setIsActive(true);
        }
        return employeeRepo.save(emp);
    }
    
    @Override
    public Employee update(Long id, EmployeeDto dto) {
        Employee emp = getById(id);
        mapper.employee().partialUpdate(dto, emp);
        return employeeRepo.save(emp);
    }
    
    @Override
    public void delete(Long id) {
        Employee emp = getById(id);
        employeeRepo.delete(emp);
    }
    
    @Override
    public Page<Employee> searchEmployees(SearchCriteria search, Pageable pageable) {
        Specification<Employee> specs = Specification.where(null);
        if(!search.getName().isBlank()){
            specs = specs.and(EmployeeSpecification.nameLikes(search.getName()));
        }
        if(search.getIsManager() != null){
            specs = specs.and(EmployeeSpecification.isManager(search.getIsManager()));
        }
        if(search.getContractType() != null){
            specs = specs.and(EmployeeSpecification.contractType(search.getContractType()));
        }
        if(search.getSalaryFrom() != null){
            specs = specs.and(EmployeeSpecification.fromSalary(search.getSalaryFrom()));
        }
        if(search.getSalaryTo() != null){
            specs = specs.and(EmployeeSpecification.toSalary(search.getSalaryTo()));
        }
        return employeeRepo.findAll(specs, pageable);
    }
    
    @Override
    public List<Employee> findManagerByHireDate(LocalDate hireDate) {
        return employeeRepo.findByManager_IdNullAndContracts_HireDateAfter(hireDate);
    }
    
    @Override
    public Employee findByContractNr(String contractNr) {
        return employeeRepo.findEmployeeByContractNr(contractNr).orElseThrow(() -> new NotFoundException("Employee not found"));
    }
}
