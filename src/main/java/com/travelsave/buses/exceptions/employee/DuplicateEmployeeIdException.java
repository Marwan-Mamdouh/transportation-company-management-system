package com.travelsave.buses.exceptions.employee;

import com.travelsave.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmployeeIdException extends RuntimeException {

  public DuplicateEmployeeIdException() {
    super(ErrorMessage.DUPLICATE_EMPLOYEE_ID.getMessage());
  }
}