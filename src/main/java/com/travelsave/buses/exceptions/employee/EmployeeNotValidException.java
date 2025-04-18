package com.travelsave.buses.exceptions.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotValidException extends RuntimeException {

  public EmployeeNotValidException(String message) {
    super(message);
  }
}
