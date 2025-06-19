package com.travelSafe.buses.domain.department.controller;

import com.travelSafe.buses.domain.department.DTO.DepartmentResponseDTO;
import com.travelSafe.buses.domain.department.model.Department;
import com.travelSafe.buses.domain.department.service.GetDepartmentsService;
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
  public ResponseEntity<List<DepartmentResponseDTO>> getDepartments() {
    final List<Department> department = getDepartmentsService.execute(null);
    return ResponseEntity.ok(department.stream().map(DepartmentResponseDTO::new).toList());
  }
}