package com.travel.safe.buses.domain.employee.model;

import com.travel.safe.buses.domain.employee.enums.Role;

public interface EmployeeAuth {

  String getEmail();

  String getPassword();

  Role getRole();
}