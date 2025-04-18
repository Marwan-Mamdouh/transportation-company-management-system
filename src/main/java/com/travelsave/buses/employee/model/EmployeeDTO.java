package com.travelsave.buses.employee.model;

import lombok.Data;

@Data
public class EmployeeDTO {

  private final String ssn, fName, lName, email, phoneNumber, departmentName;

  public EmployeeDTO(Employee employee) {
    this.ssn = employee.getEmployeeId();
    this.fName = employee.getFirstName();
    this.lName = employee.getLastName();
    this.email = employee.getEmail();
    this.phoneNumber = employee.getPhoneNumber();
    this.departmentName =
        employee.getDepartment() == null ? "no department" : employee.getDepartment().getName();
  }
}