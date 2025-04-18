package com.travelsave.buses.exceptions.department;

import com.travelsave.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {

  public DepartmentNotFoundException() {
    super(ErrorMessage.DEPARTMENT_NOT_FOUND.getMessage());
  }
}
