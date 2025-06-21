package com.travel.safe.buses.comman.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
  EMPLOYEE_NOT_FOUND("Employee not found."), DUPLICATE_EMPLOYEE_ID(
      "Duplicate employee Id."), EMAIL_NOT_FOUND("Email not valid"), PASSWORD_NOT_VALID(
      "Password Not valid."), DUPLICATE_EMAIL("Duplicate email."), DUPLICATE_PHONE_NUMBER(
      "Duplicate phone number."), SUPERVISOR_NOT_FOUND(
      "Supervisor not found."), DEPARTMENT_NOT_FOUND(
      "Department not found."), DUPLICATE_DEPARTMENT_NAME(
      "Duplicate department name."), TRAVEL_LINE_NOT_FOUND(
      "Travel line not found."), TRAVEL_LINE_NOT_VALID("Travel line not valid."), TRIP_NOT_FOUND(
      "Trip not found."), TRIP_NOT_VALID("Trip not valid."), SEAT_NOT_FOUND(
      "Seat not found."), SEAT_ALREADY_BOOKED(
      "seat already booked by other customer."), VEHICLE_NOT_FOUND(
      "Vehicle not found."), DUPLICATE_VEHICLE_ID(
      "duplicate vehicle Id."), DUPLICATE_VEHICLE_PLATE_NUMBER("Duplicate vehicle plate number.");

  private final String message;

  ErrorMessage(String message) {
    this.message = message;
  }
}