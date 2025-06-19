package com.travelSafe.buses.domain.employee.exceptions;

import com.travelSafe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmployeePhoneNumberException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(
      DuplicateEmployeePhoneNumberException.class);

  public DuplicateEmployeePhoneNumberException() {
    super(ErrorMessage.DUPLICATE_PHONE_NUMBER.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}