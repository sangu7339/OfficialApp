package com.Vebturebiz.App.repository;
import com.Vebturebiz.App.entity.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	  List<Attendance> findByEmployee_EmpId(String empId);
	}
