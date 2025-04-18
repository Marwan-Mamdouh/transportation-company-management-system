package com.travelsave.buses.employee.model;

import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.model.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class Employee extends Person {

  @Id
  @Column(name = "ssn")
  @Pattern(regexp = "^[2-3][0-9]{13}$")
  private String employeeId;

  // Self-referencing relationship: one supervisor can supervise many employees
  @JoinColumn(name = "supervisor")
  @ManyToOne(fetch = FetchType.LAZY)
  private Employee supervisor;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  public Employee(String firstName, String lastName, String email, String phoneNumber,
      LocalDate birthday, String employeeId, Employee supervisor, Department department) {
    super(firstName, lastName, email, phoneNumber, birthday);
    this.employeeId = employeeId;
    this.supervisor = supervisor;
    this.department = department;
  }
}