package com.Vebturebiz.App.entity;

import com.Vebturebiz.App.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/** Database user for login; password is stored as a BCrypt hash. */
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames={"username","email"}))
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Column(nullable=false, unique=true)
  private String username;

  @NotBlank @Column(nullable=false)
  private String password; // BCrypt string

  @Enumerated(EnumType.STRING) @Column(nullable=false)
  private Role role;

  @Email @Column(nullable=false, unique=true)
  private String email;
}
