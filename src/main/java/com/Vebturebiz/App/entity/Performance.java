package com.Vebturebiz.App.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDate;

/** Review snapshots with rating and feedback. */
@Entity @Table(name="performance")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Performance {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="employee_id", nullable=false)
  private EmployeeDetails employee;

  private Integer rating;
  @Column(length=2000) private String feedback;
  private LocalDate reviewDate;
}
