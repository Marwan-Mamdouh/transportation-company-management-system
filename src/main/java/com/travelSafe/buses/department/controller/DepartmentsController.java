package com.travelSafe.buses.department.controller;

import com.travelSafe.buses.department.model.DTO.DepartmentDTO;
import com.travelSafe.buses.department.model.Department;
import com.travelSafe.buses.department.service.GetDepartmentsService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {

  private final GetDepartmentsService getDepartmentsService;

  public DepartmentsController(GetDepartmentsService getDepartmentsService) {
    this.getDepartmentsService = getDepartmentsService;
  }

  @GetMapping
  public ResponseEntity<List<DepartmentDTO>> getDepartments() {
    final List<Department> department = getDepartmentsService.execute(null);
    return ResponseEntity.ok(department.stream().map(DepartmentDTO::new).toList());
  }
}