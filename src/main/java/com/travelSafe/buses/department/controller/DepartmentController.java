package com.travelSafe.buses.department.controller;

import com.travelSafe.buses.department.model.DTO.CreateDepartmentDto;
import com.travelSafe.buses.department.model.DTO.DepartmentDTO;
import com.travelSafe.buses.department.model.DTO.PromoteManagerDTO;
import com.travelSafe.buses.department.model.DTO.UpdatedDepartmentDTO;
import com.travelSafe.buses.department.model.Department;
import com.travelSafe.buses.department.service.CreateDepartmentService;
import com.travelSafe.buses.department.service.DeleteDepartmentService;
import com.travelSafe.buses.department.service.GetDepartmentService;
import com.travelSafe.buses.department.service.PromoteManagerService;
import com.travelSafe.buses.department.service.UpdateDepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  private final CreateDepartmentService createDepartmentService;

  private final DeleteDepartmentService deleteDepartmentService;

  private final GetDepartmentService searchDepartmentByIdService;

  private final UpdateDepartmentService updateDepartmentService;
  private final PromoteManagerService promoteManagerService;

  public DepartmentController(CreateDepartmentService createDepartmentService,
      DeleteDepartmentService deleteDepartmentService,
      GetDepartmentService searchDepartmentByIdService,
      UpdateDepartmentService updateDepartmentService,
      PromoteManagerService promoteManagerService) {
    this.createDepartmentService = createDepartmentService;
    this.deleteDepartmentService = deleteDepartmentService;
    this.searchDepartmentByIdService = searchDepartmentByIdService;
    this.updateDepartmentService = updateDepartmentService;
    this.promoteManagerService = promoteManagerService;
  }

  @PostMapping
  public ResponseEntity<DepartmentDTO> createDepartment(
      @Valid @RequestBody CreateDepartmentDto department) {
    final Department savedDepartment = createDepartmentService.execute(department);
    return ResponseEntity.ok(new DepartmentDTO(savedDepartment));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable @Positive Integer id) {
    final Department department = searchDepartmentByIdService.execute(id);
    return ResponseEntity.ok(new DepartmentDTO(department));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<DepartmentDTO> updateDepartment(
      @Valid @RequestBody UpdatedDepartmentDTO department) {
    final Department updatedDepartment = updateDepartmentService.execute(department);
    return ResponseEntity.ok(new DepartmentDTO(updatedDepartment));
  }

  @PutMapping("promote/manager/{id}")
  public ResponseEntity<DepartmentDTO> promoteManager(@PathVariable @Positive Long id,
      @Valid @RequestBody Department department) {
    final Department updatedDepartment = promoteManagerService.execute(
        new PromoteManagerDTO(id, department));
    return ResponseEntity.ok(new DepartmentDTO(updatedDepartment));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> removeDepartment(@PathVariable @Positive Integer id) {
    deleteDepartmentService.execute(id);
    return ResponseEntity.ok().build();
  }
}