package com.travelSafe.buses.department.model.DTO;

import com.travelSafe.buses.department.model.Department;
import lombok.Data;

@Data
public class DepartmentDTO {

  private final Integer id;
  private final String name;
  private final Double workingHoursPerDay;

  public DepartmentDTO(Department department) {
    this.id = department.getId();
    this.name = department.getName();
    this.workingHoursPerDay = department.getWorkingHoursPerDay();
  }
}