package com.travelsave.buses.exceptions.employee;

import com.travelsave.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmployeePhoneNumber extends RuntimeException {

  public DuplicateEmployeePhoneNumber() {
    super(ErrorMessage.DUPLICATE_PHONE_NUMBER_REQUIRED.getMessage());
  }
}