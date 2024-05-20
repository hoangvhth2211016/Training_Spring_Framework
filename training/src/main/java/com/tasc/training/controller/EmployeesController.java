package com.tasc.training.controller;

import com.tasc.training.mapper.MapperManager;
import com.tasc.training.model.PageRes;
import com.tasc.training.model.contract.ContractType;
import com.tasc.training.model.employee.Employee;
import com.tasc.training.model.employee.EmployeeDto;
import com.tasc.training.model.employee.EmployeeRes;
import com.tasc.training.service.employees.EmployeeService;
import com.tasc.training.specification.employee.SearchCriteria;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("employees")
public class EmployeesController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private MapperManager mapper;
    
    @GetMapping
    public PageRes<EmployeeRes> getAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ){
        return new PageRes<>(employeeService.getAll(pageable).map(mapper.employee()::toRes));
    }
    
    @GetMapping("search")
    public PageRes<EmployeeRes> searchEmployees(
            SearchCriteria search,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ){
        return new PageRes<>(employeeService.searchEmployees(search, pageable).map(mapper.employee()::toRes));
    }
    
    @GetMapping("{id}")
    public EmployeeRes getById(Long id){
        return mapper.employee().toRes(employeeService.getById(id));
    }
    
    @PostMapping
    public EmployeeRes create(@RequestBody @Valid EmployeeDto dto){
        return mapper.employee().toRes(employeeService.create(dto));
    }
    
    @PutMapping("{id}")
    public EmployeeRes update(@PathVariable Long id, @RequestBody EmployeeDto dto){
        return mapper.employee().toRes(employeeService.update(id, dto));
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        employeeService.delete(id);
    }
    
    @GetMapping("/contracts")
    public EmployeeRes getByContractNr(@RequestParam(value = "contract_nr", required = true) String contractNr){
        return mapper.employee().toRes(employeeService.findByContractNr(contractNr));
    }
}
