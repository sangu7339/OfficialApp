package com.Vebturebiz.App.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetailsDto {
  private String empId;
  private String username;
  private String email;
  private String department;
  private String designation;
  private LocalDate dateOfJoining;
}
