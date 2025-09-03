package com.Vebturebiz.App.mapper;

import com.Vebturebiz.App.dto.EmployeeDetailsDto;
import com.Vebturebiz.App.entity.EmployeeDetails;

public class EmployeeMapper {

  public static EmployeeDetailsDto toDto(EmployeeDetails e) {
    if (e == null) return null;

    return EmployeeDetailsDto.builder()
        .empId(e.getEmpId())
        .username(e.getUser().getUsername())
        .email(e.getUser().getEmail())
        .department(e.getDepartment())
        .designation(e.getDesignation())
        .dateOfJoining(e.getDateOfJoining())
        .build();
  }
}
