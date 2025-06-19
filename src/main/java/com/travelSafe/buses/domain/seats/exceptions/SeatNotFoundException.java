package com.travelSafe.buses.domain.seats.exceptions;

import com.travelSafe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeatNotFoundException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(
      SeatNotFoundException.class);

  public SeatNotFoundException() {
    super(ErrorMessage.SEAT_NOT_FOUND.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}