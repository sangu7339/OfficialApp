package com.Vebturebiz.App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vebturebiz.App.entity.EmployeeDetails;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {
	  Optional<EmployeeDetails> findByEmpId(String empId);
	  boolean existsByEmpId(String empId);
	 
	}