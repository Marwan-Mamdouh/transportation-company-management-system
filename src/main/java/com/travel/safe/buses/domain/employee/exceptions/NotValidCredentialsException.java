package com.travel.safe.buses.domain.employee.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotValidCredentialsException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotValidCredentialsException.class);

  public NotValidCredentialsException() {
    super(ErrorMessage.NOT_VALID_CREDENTIALS.getMessage());
    LOGGER.debug("Exception: {}thrown.", getClass());
  }
}