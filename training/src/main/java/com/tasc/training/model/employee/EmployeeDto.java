package com.tasc.training.model.employee;

import com.tasc.training.model.contract.ContractDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @Email
    private String email;
    
    @NotBlank
    private String phone;
    
    private Long managerId;
    
    private ContractDto contractDto;
    
}
