package com.travel.safe.buses.domain.employee.model;

import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.employee.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Employee {

  @Id
  private Long ssn;

  @Column(name = "first_name", nullable = false)
  private String firstname;

  @Column(name = "last_name", nullable = false)
  private String lastname;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true, nullable = false)
  private String phoneNumber;

  @Column(name = "birth_day", nullable = false)
  private LocalDate birthday;

  @Transient
  private Integer age;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  // Self-referencing relationship: one supervisor can supervise many employees
  @JoinColumn(name = "supervisor")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Employee supervisor;

  @JoinColumn(name = "department_id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Department department;

  @Max(100)
  @Positive
  @Transient // ensures JPA doesn't try to persist this value
  public Integer getAge() {
    if (birthday == null) {
      return null;
    }
    return Period.between(birthday, LocalDate.now()).getYears();
  }
}