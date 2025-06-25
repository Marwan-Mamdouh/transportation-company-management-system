package com.travel.safe.buses.domain.department;

import com.travel.safe.buses.domain.department.dto.CreateDepartmentDto;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.dto.DepartmentSearchDTO;
import com.travel.safe.buses.domain.department.dto.PromoteManagerDTO;
import com.travel.safe.buses.domain.department.dto.UpdatedDepartmentDTO;
import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.department.service.CreateDepartmentService;
import com.travel.safe.buses.domain.department.service.DeleteDepartmentService;
import com.travel.safe.buses.domain.department.service.GetDepartmentService;
import com.travel.safe.buses.domain.department.service.GetDepartmentsService;
import com.travel.safe.buses.domain.department.service.PromoteManagerService;
import com.travel.safe.buses.domain.department.service.UpdateDepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/departments")
public class DepartmentController {

  private final CreateDepartmentService createDepartmentService;

  private final DeleteDepartmentService deleteDepartmentService;

  private final GetDepartmentService getDepartmentService;
  private final GetDepartmentsService getDepartmentsService;

  private final UpdateDepartmentService updateDepartmentService;
  private final PromoteManagerService promoteManagerService;

  public DepartmentController(CreateDepartmentService createDepartmentService,
      DeleteDepartmentService deleteDepartmentService, GetDepartmentService getDepartmentService,
      GetDepartmentsService getDepartmentsService, UpdateDepartmentService updateDepartmentService,
      PromoteManagerService promoteManagerService) {
    this.createDepartmentService = createDepartmentService;
    this.deleteDepartmentService = deleteDepartmentService;
    this.getDepartmentService = getDepartmentService;
    this.getDepartmentsService = getDepartmentsService;
    this.updateDepartmentService = updateDepartmentService;
    this.promoteManagerService = promoteManagerService;
  }

  @PostMapping
  public ResponseEntity<DepartmentResponseDTO> createDepartment(
      @Valid @RequestBody CreateDepartmentDto department) {
    return ResponseEntity.ok(createDepartmentService.execute(department));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable @Positive Integer id) {
    return ResponseEntity.ok(getDepartmentService.execute(id));
  }

  @GetMapping
  public ResponseEntity<Page<Department>> getDepartments(
      @RequestBody DepartmentSearchDTO departmentSearchDTO) {
    final Page<Department> departments = getDepartmentsService.execute(departmentSearchDTO);
    return ResponseEntity.ok(departments);
  }

  @PutMapping("/update")
  public ResponseEntity<DepartmentResponseDTO> updateDepartment(
      @Valid @RequestBody UpdatedDepartmentDTO department) {
    return ResponseEntity.ok(updateDepartmentService.execute(department));
  }

  @PutMapping("promote/manager")
  public ResponseEntity<DepartmentResponseDTO> promoteManager(
      @Valid @RequestBody PromoteManagerDTO dto) {
    return ResponseEntity.ok(promoteManagerService.execute(dto));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> removeDepartment(@PathVariable @Positive Integer id) {
    deleteDepartmentService.execute(id);
    return ResponseEntity.ok().build();
  }
}