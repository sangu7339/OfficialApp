package com.Vebturebiz.App.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmployeeRequest {
  @NotBlank private String username;
  @Email @NotBlank private String email;
  @NotBlank private String password; // raw; server encodes BCrypt
  @NotBlank private String empId;
  private String department;
  private String designation;
  private LocalDate dateOfJoining;
}
