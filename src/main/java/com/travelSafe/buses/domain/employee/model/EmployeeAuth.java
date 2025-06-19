package com.travelSafe.buses.domain.employee.model;

import com.travelSafe.buses.domain.employee.enums.Role;

public interface EmployeeAuth {

  String getEmail();

  String getPassword();

  Role getRole();
}