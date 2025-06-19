package com.travelSafe.buses.domain.employee.exceptions;

import com.travelSafe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmployeeEmailException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(
      DuplicateEmployeeEmailException.class);

  public DuplicateEmployeeEmailException() {
    super(ErrorMessage.DUPLICATE_EMAIL.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}