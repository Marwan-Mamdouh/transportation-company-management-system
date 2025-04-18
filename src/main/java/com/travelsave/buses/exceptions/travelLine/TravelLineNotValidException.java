package com.travelsave.buses.exceptions.travelLine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TravelLineNotValidException extends RuntimeException {

  public TravelLineNotValidException(String message) {
    super(message);
  }
}
