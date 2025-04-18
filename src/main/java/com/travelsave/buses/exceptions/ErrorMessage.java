package com.travelsave.buses.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
  CUSTOMER_NOT_FOUND("Customer Not Found."), CUSTOMER_ID_REQUIRED(
      "Customer id can't be null or zero."), FIRST_NAME_REQUIRED(
      "First name Required."), LAST_NAME_REQUIRED("Last name Required."), EMAIL_REQUIRED(
      "Email Required."), DUPLICATE_PHONE_NUMBER_REQUIRED(
      "phone number must be unique."), DATE_REQUIRED("Date Required."), NOT_VALID_EMAIL(
      "Not Valid email."), DUPLICATE_PHONE_NUMBER("Duplicate phone number."), NOT_VALID_DATE(
      "Not Valid date."), DEPARTMENT_NOT_FOUND("Department not found"), DEPARTMENT_NAME_NOT_VALID(
      "department name not valid"), DEPARTMENT_NAME_REQUIRED(
      "Department name Required."), SALARY_REQUIRED(
      "Salary can't be null or zero."), DEPARTMENT_WORKING_HOURS_REQUIRED(
      "Department working hours can't be null or zero."), DEPARTMENT_MANAGER_ID_REQUIRED(
      "Department manager Id can't be null or zero."), EMPLOYEE_NOT_FOUND(
      "Employee not found."), SUPERVISOR_NOT_FOUND("supervisor not found."), DUPLICATE_EMPLOYEE_ID(
      "duplicate employee data."), TRAVEL_LINE_NOT_FOUND(
      "Travel line not found."), TRAVEL_LINE_NOT_VALID("Travel line not valid."), TRIP_NOT_FOUND(
      "Trip not found."), TRIP_NOT_VALID("Trip not valid.");

  private final String message;

  ErrorMessage(String message) {
    this.message = message;
  }
}