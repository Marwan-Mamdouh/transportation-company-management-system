package com.travelSafe.buses.domain.seats.exceptions;

import com.travelSafe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SeatAlreadyBookedException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(SeatAlreadyBookedException.class);

  public SeatAlreadyBookedException() {
    super(ErrorMessage.SEAT_ALREADY_BOOKED.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}