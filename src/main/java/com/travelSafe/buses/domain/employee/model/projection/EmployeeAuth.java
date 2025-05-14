package com.travelSafe.buses.domain.employee.model.projection;

public interface EmployeeAuth {

  String getEmail();

  String getPassword();

  String getRole();
}