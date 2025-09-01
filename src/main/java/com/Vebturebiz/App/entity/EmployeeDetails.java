package com.Vebturebiz.App.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import java.time.LocalDate;

/** Business profile bound 1:1 to a User; empId is the HR-visible identifier. */
@Entity
@Table(name="employee_details", uniqueConstraints=@UniqueConstraint(columnNames="empId"))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDetails {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, unique=true)
  private String empId;

  @OneToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false)
  private User user;

  private String department;
  private String designation;
  private LocalDate dateOfJoining;
}
