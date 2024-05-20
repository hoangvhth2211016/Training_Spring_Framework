package com.tasc.training.specification.employee;

import com.tasc.training.model.contract.ContractType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SearchCriteria {
    
    private String name;
    
    private Boolean isManager;
    
    private ContractType contractType;
    
    private BigDecimal salaryFrom;
    
    private BigDecimal salaryTo;
}
