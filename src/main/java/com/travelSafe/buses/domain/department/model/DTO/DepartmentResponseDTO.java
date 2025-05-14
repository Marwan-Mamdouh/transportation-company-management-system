package com.travelSafe.buses.domin.department.model.DTO;

import com.travelSafe.buses.domin.department.model.Department;
import lombok.Data;

@Data
public class DepartmentResponseDTO {

  private final Integer id;
  private final String name;
  private final Double workingHoursPerDay;

  public DepartmentResponseDTO(Department department) {
    this.id = department.getId();
    this.name = department.getName();
    this.workingHoursPerDay = department.getWorkingHoursPerDay();
  }
}