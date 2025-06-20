package com.travelSafe.buses.domain.department.controller;

import com.travelSafe.buses.domain.department.DTO.CreateDepartmentDto;
import com.travelSafe.buses.domain.department.DTO.DepartmentResponseDTO;
import com.travelSafe.buses.domain.department.DTO.PromoteManagerDTO;
import com.travelSafe.buses.domain.department.DTO.UpdatedDepartmentDTO;
import com.travelSafe.buses.domain.department.model.Department;
import com.travelSafe.buses.domain.department.service.CreateDepartmentService;
import com.travelSafe.buses.domain.department.service.DeleteDepartmentService;
import com.travelSafe.buses.domain.department.service.GetDepartmentService;
import com.travelSafe.buses.domain.department.service.PromoteManagerService;
import com.travelSafe.buses.domain.department.service.UpdateDepartmentService;
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
@RequestMapping("/api/v1/department")
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
  public ResponseEntity<DepartmentResponseDTO> createDepartment(
      @Valid @RequestBody CreateDepartmentDto department) {
    final Department savedDepartment = createDepartmentService.execute(department);
    return ResponseEntity.ok(new DepartmentResponseDTO(savedDepartment));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable @Positive Integer id) {
    final Department department = searchDepartmentByIdService.execute(id);
    return ResponseEntity.ok(new DepartmentResponseDTO(department));
  }

  @PutMapping("/update")
  public ResponseEntity<DepartmentResponseDTO> updateDepartment(
      @Valid @RequestBody UpdatedDepartmentDTO department) {
    final Department updatedDepartment = updateDepartmentService.execute(department);
    return ResponseEntity.ok(new DepartmentResponseDTO(updatedDepartment));
  }

  @PutMapping("promote/manager")
  public ResponseEntity<DepartmentResponseDTO> promoteManager(
      @Valid @RequestBody PromoteManagerDTO dto) {
    final Department updatedDepartment = promoteManagerService.execute(dto);
    return ResponseEntity.ok(new DepartmentResponseDTO(updatedDepartment));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> removeDepartment(@PathVariable @Positive Integer id) {
    deleteDepartmentService.execute(id);
    return ResponseEntity.ok().build();
  }
}