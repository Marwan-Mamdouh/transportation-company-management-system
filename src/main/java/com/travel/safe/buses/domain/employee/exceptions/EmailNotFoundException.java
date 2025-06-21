package com.travel.safe.buses.domain.employee.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException() {
    super(ErrorMessage.EMAIL_NOT_FOUND.getMessage());
  }
}