package com.travelsave.buses.exceptions.employee;

import com.travelsave.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException() {
    super(ErrorMessage.EMPLOYEE_NOT_FOUND.getMessage());
  }
}
