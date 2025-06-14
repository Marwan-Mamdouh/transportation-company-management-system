package com.travelSafe.buses.exceptions.employee;

import com.travelSafe.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException() {
    super(ErrorMessage.EMAIL_NOT_FOUND.getMessage());
  }
}