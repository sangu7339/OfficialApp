package com.Vebturebiz.App.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UpdateEmployeeRequest {
  private String department;
  private String designation;
  private LocalDate dateOfJoining;
}
