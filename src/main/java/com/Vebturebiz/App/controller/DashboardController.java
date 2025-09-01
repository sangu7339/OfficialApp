package com.Vebturebiz.App.controller;

import com.Vebturebiz.App.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController 
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
  private final EmployeeRepository employeeRepo;
  private final AttendanceRepository attendanceRepo;
  private final LeaveRepository leaveRepo;
  private final PerformanceRepository performanceRepo;

  @GetMapping("/hr")
  public ResponseEntity<Map<String,Object>> hrMetrics() {
    Map<String,Object> m = new HashMap<>();
    m.put("employees", employeeRepo.count());
    m.put("attendanceRecords", attendanceRepo.count());
    m.put("pendingLeaves", leaveRepo.findAll().stream().filter(l -> "PENDING".equalsIgnoreCase(l.getStatus())).count());
    m.put("reviews", performanceRepo.count());
    return ResponseEntity.ok(m);
  }

  @GetMapping("/employee")
  public ResponseEntity<Map<String,Object>> employeeDashboard(Authentication auth) {
    String username = auth.getName();
    var emp = employeeRepo.findAll().stream().filter(e -> e.getUser().getUsername().equals(username)).findFirst().orElseThrow();
    Map<String,Object> m = new HashMap<>();
    m.put("empId", emp.getEmpId());		
    m.put("attendance", attendanceRepo.findByEmployee_EmpId(emp.getEmpId()));
    m.put("leaves", leaveRepo.findByEmployee_EmpId(emp.getEmpId()));
    m.put("performance", performanceRepo.findByEmployee_EmpId(emp.getEmpId()));
    return ResponseEntity.ok(m);
  }

  @GetMapping("/insights")
  public ResponseEntity<Map<String,String>> insights() {
    Map<String,String> res = new HashMap<>();
    res.put("note","Placeholder for GPT-5 powered recommendations");
    return ResponseEntity.ok(res);
  }
}
