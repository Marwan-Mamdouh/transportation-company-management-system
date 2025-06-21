package com.travel.safe.buses.domain.trip.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TripNotValidException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(TripNotValidException.class);

  public TripNotValidException() {
    super(ErrorMessage.TRIP_NOT_VALID.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}