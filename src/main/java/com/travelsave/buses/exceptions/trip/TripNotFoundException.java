package com.travelsave.buses.exceptions.trip;

import com.travelsave.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TripNotFoundException extends RuntimeException {

  public TripNotFoundException() {
    super(ErrorMessage.TRIP_NOT_FOUND.getMessage());
  }
}
