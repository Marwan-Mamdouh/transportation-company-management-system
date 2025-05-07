package com.travelSafe.buses.employee.model.DTO;

import com.travelSafe.buses.employee.model.Employee;
import lombok.Data;

@Data
public class EmployeePaycheckDTO {

  private final Long ssn;
  private final String firstname, lastname;
  private final Integer salary;

  public EmployeePaycheckDTO(Employee employee) {
    this.ssn = employee.getSsn();
    this.firstname = employee.getFirstName();
    this.lastname = employee.getLastName();
    this.salary = employee.getDepartment() == null ? null : employee.getDepartment().getSalary();
  }
}