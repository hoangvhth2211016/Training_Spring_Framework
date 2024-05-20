package com.tasc.training.specification.employee;

import com.tasc.training.model.contract.Contract;
import com.tasc.training.model.contract.ContractType;
import com.tasc.training.model.employee.Employee;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class EmployeeSpecification {
    
    public static Specification<Employee> nameLikes(String name){
        return (root, query, cb) -> {
            return cb.like(
                    cb.concat(
                            cb.concat(root.get("firstName"), " "), root.get("lastName")),
                    "%" + name.toLowerCase() + "%");
        };
    }
    
    public static  Specification<Employee> isManager(Boolean isManager){
        return (root, query, cb) -> {
            return isManager ? cb.isNull(root.get("manager")) : cb.isNotNull(root.get("manager"));
        };
    }
    
    public static Specification<Employee> contractType(ContractType type){
        return (root, query, cb) -> {
            return cb.equal(employeeContract(root).get("type"), type);
        } ;
    }
    
    public static Specification<Employee> fromSalary(BigDecimal from){
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(employeeContract(root).get("salary"), from);
        };
    }
    public static Specification<Employee> toSalary(BigDecimal to){
        return (root, query, cb) -> {
            return cb.lessThanOrEqualTo(employeeContract(root).get("salary"), to);
        };
    }
    
    private static Join<Employee, Contract> employeeContract(Root<Employee> root) {
        return root.join("contracts");
    }
}
