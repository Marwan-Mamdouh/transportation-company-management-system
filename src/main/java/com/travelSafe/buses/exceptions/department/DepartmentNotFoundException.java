package com.travelSafe.buses.exceptions.department;

import com.travelSafe.buses.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(DepartmentNotFoundException.class);

  public DepartmentNotFoundException() {
    super(ErrorMessage.DEPARTMENT_NOT_FOUND.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}