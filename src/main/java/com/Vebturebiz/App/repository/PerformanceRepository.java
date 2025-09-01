package com.Vebturebiz.App.repository;

import com.Vebturebiz.App.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Exposes standard CRUD and count() via Spring Data JPA.
 */
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
  List<Performance> findByEmployee_EmpId(String empId);
}
