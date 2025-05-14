package com.travelSafe.buses.exceptions.employee;

import com.travelSafe.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotValidPasswordException extends RuntimeException {

  public NotValidPasswordException() {
    super(ErrorMessage.PASSWORD_NOT_VALID.getMessage());
  }
}