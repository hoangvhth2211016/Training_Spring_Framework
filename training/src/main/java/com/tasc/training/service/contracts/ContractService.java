package com.tasc.training.service.contracts;

import com.tasc.training.model.contract.Contract;
import com.tasc.training.model.contract.ContractDto;
import com.tasc.training.model.contract.ContractRes;

import java.util.List;

public interface ContractService {

    List<ContractRes> getAll();

    ContractRes getById(Long id);
 
    Contract create(ContractDto dto);
}
