package com.travelsave.buses.exceptions.customer;

import com.travelsave.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

  public CustomerNotFoundException() {
    super(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage());
  }
}
