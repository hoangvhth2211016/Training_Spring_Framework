package com.tasc.training.controller;

import com.tasc.training.model.contract.Contract;
import com.tasc.training.model.contract.ContractDto;
import com.tasc.training.model.contract.ContractRes;
import com.tasc.training.service.contracts.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contracts")
public class ContractsController {

    @Autowired
    private ContractService contractService;
    
    @GetMapping
    public List<ContractRes> getAll(){
        return contractService.getAll();
    }
   
    @GetMapping("{id}")
    public ContractRes getById(Long id){
        return contractService.getById(id);
    }
    
    @PostMapping
    public Contract create(ContractDto dto){
        return contractService.create(dto);
    }
    
    
    
}
