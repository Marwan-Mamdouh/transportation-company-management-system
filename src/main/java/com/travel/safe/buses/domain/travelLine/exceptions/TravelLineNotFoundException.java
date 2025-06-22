package com.travel.safe.buses.domain.travelLine.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TravelLineNotFoundException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory.getLogger(TravelLineNotFoundException.class);

  public TravelLineNotFoundException() {
    super(ErrorMessage.TRAVEL_LINE_NOT_FOUND.getMessage());
    LOGGER.error("Exception: {}thrown.", getClass());
  }
}