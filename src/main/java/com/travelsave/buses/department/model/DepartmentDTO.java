package com.travelsave.buses.department.model;

import lombok.Data;

@Data
public class DepartmentDTO {

  private final Integer id;
  private final String name;
  private final String managerFirstname;

  public DepartmentDTO(Department department) {
    this.id = department.getId();
    this.name = department.getName();
    this.managerFirstname = department.getDepartmentManager().getFirstName();
  }
}