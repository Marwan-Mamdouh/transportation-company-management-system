package com.travelSafe.buses.domin.employee.model.DTO;

import com.travelSafe.buses.domin.employee.model.Employee;
import lombok.Data;

@Data
public class EmployeeResponseDTO {

  private final Long ssn;
  private final String fName, lName, email, phoneNumber;

  public EmployeeResponseDTO(Employee employee) {
    this.ssn = employee.getSsn();
    this.fName = employee.getFirstname();
    this.lName = employee.getLastname();
    this.email = employee.getEmail();
    this.phoneNumber = employee.getPhoneNumber();
  }
}