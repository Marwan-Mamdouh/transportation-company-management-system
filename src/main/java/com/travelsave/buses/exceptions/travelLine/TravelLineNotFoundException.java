package com.travelsave.buses.exceptions.travelLine;

import com.travelsave.buses.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TravelLineNotFoundException extends RuntimeException {

  public TravelLineNotFoundException() {
    super(ErrorMessage.TRAVEL_LINE_NOT_FOUND.getMessage());
  }
}
