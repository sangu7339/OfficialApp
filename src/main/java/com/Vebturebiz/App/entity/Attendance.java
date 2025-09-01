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
import jakarta.persistence.UniqueConstraint;

/** One row per employee per day describing presence status. */
@Entity
@Table(name="attendance", uniqueConstraints=@UniqueConstraint(columnNames={"employee_id","date"}))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Attendance {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="employee_id", nullable=false)
  private EmployeeDetails employee;

  @Column(nullable=false)
  private LocalDate date;

  @Column(nullable=false)
  private String status; // PRESENT/ABSENT/WFH etc.
}
