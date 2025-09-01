package com.Vebturebiz.App.entity;

import lombok.*;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/** Leave application with type and approval status. */
@Entity @Table(name="leaves")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Leave {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="employee_id", nullable=false)
  private EmployeeDetails employee;

  @Column(nullable=false)
  private String type; // SICK/CASUAL/ANNUAL etc.

  private LocalDate startDate;
  private LocalDate endDate;

  @Column(nullable=false)
  private String status; // PENDING/APPROVED/REJECTED
}
