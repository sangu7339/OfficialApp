package com.Vebturebiz.App.controller;

import com.Vebturebiz.App.dto.CreateEmployeeRequest;
import com.Vebturebiz.App.dto.UpdateEmployeeRequest;
import com.Vebturebiz.App.entity.EmployeeDetails;
import com.Vebturebiz.App.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Boot 3 uses Jakarta Validation
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

   @PreAuthorize("hasRole('HR')")
  @PostMapping("/create")
  public ResponseEntity<EmployeeDetails> create(@Valid @RequestBody CreateEmployeeRequest req) {
    return ResponseEntity.ok(employeeService.createEmployee(req));
  }

 @PreAuthorize("hasAnyRole('HR','EMPLOYEE')")
  @GetMapping("/{empId}")
  public ResponseEntity<EmployeeDetails> get(@PathVariable String empId) {
    return ResponseEntity.ok(
      employeeService.findByEmpId(empId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"))
    );
  }

  @PreAuthorize("hasRole('HR')")
  @PutMapping("/update/{empId}")
  public ResponseEntity<EmployeeDetails> update(@PathVariable String empId, @Valid @RequestBody UpdateEmployeeRequest req) {
    return ResponseEntity.ok(employeeService.update(empId, req));
  }

   @PreAuthorize("hasRole('HR')")
  @DeleteMapping("/delete/{empId}")
  public ResponseEntity<Void> delete(@PathVariable String empId) {
    employeeService.delete(empId);
    return ResponseEntity.noContent().build();
  }
}
