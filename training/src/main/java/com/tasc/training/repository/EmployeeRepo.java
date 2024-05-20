package com.tasc.training.repository;

import com.tasc.training.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    
    List<Employee> findByManager_IdNullAndContracts_HireDateAfter(@NonNull LocalDate hireDate);
    
    @Query("select e from Employee e join Contract c on e.id = c.employee.id where contractNr = :contractNr")
    Optional<Employee> findEmployeeByContractNr(@Param("contractNr") String contractNr);
    
}
