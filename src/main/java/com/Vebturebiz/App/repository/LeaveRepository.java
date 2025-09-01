package com.Vebturebiz.App.repository;
import com.Vebturebiz.App.entity.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
	  List<Leave> findByEmployee_EmpId(String empId);
	}
