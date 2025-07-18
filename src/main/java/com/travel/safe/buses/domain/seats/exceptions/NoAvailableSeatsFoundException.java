package com.travel.safe.buses.domain.seats.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoAvailableSeatsFoundException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      NoAvailableSeatsFoundException.class);

  public NoAvailableSeatsFoundException() {
    super(ErrorMessage.SEAT_NOT_FOUND.getMessage());
    LOGGER.error("Exception: {}thrown.", getClass());
  }
}