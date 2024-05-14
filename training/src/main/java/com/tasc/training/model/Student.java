package com.tasc.training.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    
    @NotNull
    private Integer id;
    
    @NotBlank
    private String name;
    
    @Email
    private String email;
    
    private String major;
    
}
