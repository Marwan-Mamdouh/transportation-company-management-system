package com.travelSafe.buses.employee.model.DTO;

import com.travelSafe.buses.employee.model.Employee;
import lombok.Data;

@Data
public class EmployeeResponseDTO {

  private final Long ssn;
  private final String fName, lName, email, phoneNumber, departmentName;

  public EmployeeResponseDTO(Employee employee) {
    this.ssn = employee.getSsn();
    this.fName = employee.getFirstName();
    this.lName = employee.getLastName();
    this.email = employee.getEmail();
    this.phoneNumber = employee.getPhoneNumber();
    this.departmentName =
        employee.getDepartment() == null ? "no department" : employee.getDepartment().getName();
  }
}