package com.Vebturebiz.App.service;

import com.Vebturebiz.App.dto.CreateEmployeeRequest;
import com.Vebturebiz.App.dto.UpdateEmployeeRequest;
import com.Vebturebiz.App.entity.*;
import com.Vebturebiz.App.repository.EmployeeRepository;
import com.Vebturebiz.App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/** HR operations for creating, reading, updating, and deleting employees. */
@Service @RequiredArgsConstructor
public class EmployeeService {
  private final EmployeeRepository employeeRepo;
  private final UserService userService;
  private final UserRepository userRepo;

  @Transactional
  public EmployeeDetails createEmployee(CreateEmployeeRequest req) {
    User u = userService.createUser(req.getUsername(), req.getEmail(), req.getPassword(), Role.EMPLOYEE);
    EmployeeDetails e = EmployeeDetails.builder()
        .empId(req.getEmpId())
        .user(u)
        .department(req.getDepartment())
        .designation(req.getDesignation())
        .dateOfJoining(req.getDateOfJoining())
        .build();
    return employeeRepo.save(e);
  }

  public Optional<EmployeeDetails> findByEmpId(String empId) { return employeeRepo.findByEmpId(empId); }

  @Transactional
  public EmployeeDetails update(String empId, UpdateEmployeeRequest req) {
    EmployeeDetails e = employeeRepo.findByEmpId(empId).orElseThrow(() -> new IllegalArgumentException("Not found"));
    if (req.getDepartment()!=null) e.setDepartment(req.getDepartment());
    if (req.getDesignation()!=null) e.setDesignation(req.getDesignation());
    if (req.getDateOfJoining()!=null) e.setDateOfJoining(req.getDateOfJoining());
    return employeeRepo.save(e);
  }

  @Transactional
  public void delete(String empId) {
    EmployeeDetails e = employeeRepo.findByEmpId(empId).orElseThrow(() -> new IllegalArgumentException("Not found"));
    Long userId = e.getUser().getId();
    employeeRepo.delete(e);
    userRepo.deleteById(userId);
  }
}
