package com.travelsave.buses.exceptions.trip;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TripNotValidException extends RuntimeException {

  public TripNotValidException(String message) {
    super(message);
  }
}
