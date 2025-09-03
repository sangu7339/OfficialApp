package com.Vebturebiz.App.controller;

import com.Vebturebiz.App.dto.CreateEmployeeRequest;
import com.Vebturebiz.App.dto.UpdateEmployeeRequest;
import com.Vebturebiz.App.dto.EmployeeDetailsDto;
import com.Vebturebiz.App.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  // ✅ HR can create new employees
  @PreAuthorize("hasRole('HR')")
  @PostMapping("/create")
  public ResponseEntity<EmployeeDetailsDto> create(@Valid @RequestBody CreateEmployeeRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(employeeService.createEmployee(req));
  }

  // ✅ HR and Employee can view employee details
  @PreAuthorize("hasAnyRole('HR','EMPLOYEE')")
  @GetMapping("/{empId}")
  public ResponseEntity<EmployeeDetailsDto> get(@PathVariable String empId) {
    return employeeService.findByEmpId(empId)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
  }

  // ✅ HR can update employee details
  @PreAuthorize("hasRole('HR')")
  @PutMapping("/update/{empId}")
  public ResponseEntity<EmployeeDetailsDto> update(@PathVariable String empId,
                                                   @Valid @RequestBody UpdateEmployeeRequest req) {
    return ResponseEntity.ok(employeeService.update(empId, req));
  }

  // ✅ HR can delete employees
  @PreAuthorize("hasRole('HR')")
  @DeleteMapping("/delete/{empId}")
  public ResponseEntity<Void> delete(@PathVariable String empId) {
    employeeService.delete(empId);
    return ResponseEntity.noContent().build();
  }
}
