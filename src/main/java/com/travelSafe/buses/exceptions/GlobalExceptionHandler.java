package com.travelSafe.buses.exceptions;

import com.travelSafe.buses.exceptions.department.DepartmentNotFoundException;
import com.travelSafe.buses.exceptions.travelLine.TravelLineNotFoundException;
import com.travelSafe.buses.exceptions.travelLine.TravelLineNotValidException;
import com.travelSafe.buses.exceptions.trip.TripNotFoundException;
import com.travelSafe.buses.exceptions.trip.TripNotValidException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ErrorResponse handleCustomerNotValidException(ConstraintViolationException exception) {
    return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(DepartmentNotFoundException.class)
  public ErrorResponse handleDepartmentNotFoundException(DepartmentNotFoundException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(TravelLineNotValidException.class)
  private ErrorResponse handleTravelLineNotValidException(TravelLineNotValidException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(TravelLineNotFoundException.class)
  public ErrorResponse handleTravelLineNotFoundException(TravelLineNotFoundException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(TripNotFoundException.class)
  public ErrorResponse handleTripNotFoundException(TripNotFoundException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(TripNotValidException.class)
  public ErrorResponse handleTripNotValidException(TripNotValidException exception) {
    return new ErrorResponse(exception.getMessage());
  }
}