package com.travelSafe.buses.domain.employee.model.projection;

import com.travelSafe.buses.domain.employee.model.enums.Role;

public interface EmployeeAuth {

  String getEmail();

  String getPassword();

  Role getRole();
}