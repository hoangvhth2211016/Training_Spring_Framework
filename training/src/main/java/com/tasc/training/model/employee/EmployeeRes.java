package com.tasc.training.model.employee;

import com.tasc.training.model.contract.ContractRes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EmployeeRes {

    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String phone;
    
    private List<ContractRes> contracts;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
}
