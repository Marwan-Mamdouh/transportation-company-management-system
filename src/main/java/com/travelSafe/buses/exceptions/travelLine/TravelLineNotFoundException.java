package com.travelSafe.buses.exceptions.travelLine;

import com.travelSafe.buses.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TravelLineNotFoundException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(
      TravelLineNotFoundException.class);

  public TravelLineNotFoundException() {
    super(ErrorMessage.TRAVEL_LINE_NOT_FOUND.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}