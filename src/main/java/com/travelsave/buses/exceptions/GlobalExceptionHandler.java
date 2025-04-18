package com.travelsave.buses.exceptions;

import com.travelsave.buses.exceptions.customer.CustomerNotFoundException;
import com.travelsave.buses.exceptions.customer.CustomerNotValidException;
import com.travelsave.buses.exceptions.department.DepartmentNotFoundException;
import com.travelsave.buses.exceptions.travelLine.TravelLineNotFoundException;
import com.travelsave.buses.exceptions.travelLine.TravelLineNotValidException;
import com.travelsave.buses.exceptions.trip.TripNotFoundException;
import com.travelsave.buses.exceptions.trip.TripNotValidException;
import com.travelsave.buses.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(CustomerNotFoundException.class)
  public ErrorResponse handleCustomerNotFound(CustomerNotFoundException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CustomerNotValidException.class)
  public ErrorResponse handleCustomerNotValidException(CustomerNotValidException exception) {
    return new ErrorResponse(exception.getMessage());
  }

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