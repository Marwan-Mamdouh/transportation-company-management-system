package com.travelSafe.buses.exceptions.employee;

import com.travelSafe.buses.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeNotFoundException.class);

  public EmployeeNotFoundException() {
    super(ErrorMessage.EMPLOYEE_NOT_FOUND.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}
