package com.tasc.training.model.contract;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ContractRes {
    
    private Long id;
    
    private String contractNr;
    
    private ContractType type;
    
    private LocalDate hireDate;
    
    private LocalDate expireDate;
    
    private BigDecimal salary;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
}
