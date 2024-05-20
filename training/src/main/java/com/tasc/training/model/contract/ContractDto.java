package com.tasc.training.model.contract;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ContractDto {

    @NotNull
    private ContractType type;
    
    @NotNull
    private LocalDate hireDate;
    
    private Long employeeId;
    
    @NotNull
    private BigDecimal salary;
    
    
    public Contract toEntity(){
        return Contract.builder()
                .contractNr(UUID.randomUUID().toString())
                .type(this.type)
                .salary(this.salary)
                .hireDate(this.hireDate)
                .expireDate(LocalDate.now().plusYears(1))
                .build();
    }
    
}
