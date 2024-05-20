package com.tasc.training.service.contracts;

import com.tasc.training.exception.NotFoundException;
import com.tasc.training.mapper.MapperManager;
import com.tasc.training.model.contract.Contract;
import com.tasc.training.model.contract.ContractDto;
import com.tasc.training.model.contract.ContractRes;
import com.tasc.training.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ContractServiceImpl implements ContractService{
    
    @Autowired
    private ContractRepo contractRepo;
    
    @Autowired
    private MapperManager mapper;
    
    @Override
    public List<ContractRes> getAll() {
        return contractRepo.findAll().stream().map(mapper.contract()::toRes).toList();
    }
    
    @Override
    public ContractRes getById(Long id) {
        Contract contract = contractRepo.findById(id).orElseThrow(() -> new NotFoundException("Contract not found"));
        return mapper.contract().toRes(contract);
    }
    
    @Override
    public Contract create(ContractDto dto) {
        Contract contract = mapper.contract().toEntity(dto);
        contract.setContractNr(UUID.randomUUID().toString());
        contract.setExpireDate(LocalDate.now().plusYears(1));
        return contractRepo.save(contract);
    }
}
