package com.travel.safe.buses.domain.travelLine.exceptions;

import com.travel.safe.buses.comman.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TravelLineNotValidException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(TravelLineNotValidException.class);

  public TravelLineNotValidException() {
    super(ErrorMessage.TRAVEL_LINE_NOT_VALID.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}