package com.travelSafe.buses.department.model;

import com.travelSafe.buses.employee.model.Employee;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Department {

  @Id
  @Max(255)
  @Positive
  @Column(name = "department_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  @NotNull(message = "Department name Required.")
  @Size(min = 1, max = 55, message = "Department name length should be at least 1 character, max 55.")
  private String name;

  @Positive
  @Max(10000000)
  @NotNull(message = "Salary Required for creating department.")
  private Integer salary;


  @Column(nullable = false)
  private Double workingHoursPerDay;

  // Each department has one manager who is an employee.
  @JoinColumn(name = "department_manager")
  @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Employee departmentManager;
}