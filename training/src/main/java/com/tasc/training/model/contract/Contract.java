package com.tasc.training.model.contract;

import com.tasc.training.model.BaseEntity;
import com.tasc.training.model.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "contracts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends BaseEntity {
    
    @Column(unique = true, nullable = false)
    private String contractNr;
    
    @Enumerated(EnumType.STRING)
    private ContractType type;
    
    private LocalDate hireDate;
    
    private LocalDate expireDate;
    
    @Column(name = "salary", nullable = false, precision = 19, scale = 2)
    private BigDecimal salary;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
}
