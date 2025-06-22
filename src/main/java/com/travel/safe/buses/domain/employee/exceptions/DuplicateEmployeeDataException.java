package com.travel.safe.buses.domain.employee.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmployeeDataException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      DuplicateEmployeeDataException.class);

  public DuplicateEmployeeDataException() {
    super(ErrorMessage.DUPLICATE_EMPLOYEE_DATA.getMessage());
    LOGGER.debug("Exception: {}thrown.", getClass());
  }
}