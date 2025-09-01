package com.Vebturebiz.App.controller;

import com.Vebturebiz.App.dto.CreateEmployeeRequest;
import com.Vebturebiz.App.dto.UpdateEmployeeRequest;
import com.Vebturebiz.App.entity.EmployeeDetails;
import com.Vebturebiz.App.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // Boot 3 uses Jakarta Validation

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @PostMapping("/create")
  public ResponseEntity<EmployeeDetails> create(@Valid @RequestBody CreateEmployeeRequest req) {
    return ResponseEntity.ok(employeeService.createEmployee(req));
  }

  @GetMapping("/{empId}")
  public ResponseEntity<EmployeeDetails> get(@PathVariable String empId) {
    return ResponseEntity.ok(employeeService.findByEmpId(empId).orElseThrow(() -> new RuntimeException("Not found")));
  }

  @PutMapping("/update/{empId}")
  public ResponseEntity<EmployeeDetails> update(@PathVariable String empId, @RequestBody UpdateEmployeeRequest req) {
    return ResponseEntity.ok(employeeService.update(empId, req));
  }

  @DeleteMapping("/delete/{empId}")
  public ResponseEntity<Void> delete(@PathVariable String empId) {
    employeeService.delete(empId);
    return ResponseEntity.noContent().build();
  }
}
