package com.travelSafe.buses.exceptions.vehicle;

public class DuplicateVehiclePlateNumberException extends RuntimeException {
  public DuplicateVehiclePlateNumberException(String message) {
    super(message);
  }
}
