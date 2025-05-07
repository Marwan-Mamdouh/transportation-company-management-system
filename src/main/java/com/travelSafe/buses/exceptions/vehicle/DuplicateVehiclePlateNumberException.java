package com.travelSafe.buses.exceptions.vehicle;

import com.travelSafe.buses.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateVehiclePlateNumberException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(
      DuplicateVehiclePlateNumberException.class);

  public DuplicateVehiclePlateNumberException() {
    super(ErrorMessage.DUPLICATE_VEHICLE_PLATE_NUMBER.getMessage());
    logger.error("Exception: {}thrown.", getClass());
  }
}