package com.travel.safe.buses.domain.employee.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmployeeIdException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(DuplicateEmployeeIdException.class);

  public DuplicateEmployeeIdException() {
    super(ErrorMessage.DUPLICATE_EMPLOYEE_ID.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}