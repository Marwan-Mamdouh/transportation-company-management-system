package com.travelsave.buses.department.model;

import com.travelsave.buses.employee.model.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
  @Positive
  @Column(name = "department_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  @NotNull(message = "Department name Required.")
  @Size(min = 1, max = 55, message = "Department name length should be at least 1 character, max 55.")
  private String name;

  @Positive
  @NotNull(message = "Salary Required for creating department.")
  private Double salary;

  @Positive
  @Column(name = "working_hours_per_day", nullable = false)
  @NotNull(message = "Working hours Required for creating department.")
  private Double workingHoursPerDay;

  // Each department has one manager who is an employee.
  @OneToOne
  @JoinColumn(name = "department_manager")
  private Employee departmentManager;
}