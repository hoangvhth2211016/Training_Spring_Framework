package com.tasc.training.repository;

import com.tasc.training.model.contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends JpaRepository<Contract, Long> {
}
