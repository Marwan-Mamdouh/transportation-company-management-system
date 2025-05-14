package com.travelSafe.buses.domin.employee.controller;

import com.travelSafe.buses.domin.employee.model.DTO.EmployeePaycheckDTO;
import com.travelSafe.buses.domin.employee.model.DTO.EmployeeResponseDTO;
import com.travelSafe.buses.domin.employee.model.DTO.InputEmployeeDTO;
import com.travelSafe.buses.domin.employee.model.Employee;
import com.travelSafe.buses.domin.employee.services.CreateEmployeeService;
import com.travelSafe.buses.domin.employee.services.DeleteEmployeeService;
import com.travelSafe.buses.domin.employee.services.UpdateEmployeeService;
import com.travelSafe.buses.domin.employee.services.get.GetEmployeeService;
import jakarta.validation.Valid;
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
@RequestMapping("/employee")
public class EmployeeController {

  private final CreateEmployeeService createEmployeeService;

  private final DeleteEmployeeService deleteEmployeeService;

  private final GetEmployeeService getEmployeeService;

  private final UpdateEmployeeService updateEmployeeService;

  public EmployeeController(CreateEmployeeService createEmployeeService,
      DeleteEmployeeService deleteEmployeeService, GetEmployeeService getEmployeeService,
      UpdateEmployeeService updateEmployeeService) {
    this.createEmployeeService = createEmployeeService;
    this.deleteEmployeeService = deleteEmployeeService;
    this.getEmployeeService = getEmployeeService;
    this.updateEmployeeService = updateEmployeeService;
  }

  @PostMapping
  public ResponseEntity<EmployeeResponseDTO> createEmployee(
      @Valid @RequestBody InputEmployeeDTO employee) {
    final Employee savedEmployee = createEmployeeService.execute(employee);
    return ResponseEntity.ok(new EmployeeResponseDTO(savedEmployee));
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
    final Employee employee = getEmployeeService.execute(id);
    return ResponseEntity.ok(new EmployeeResponseDTO(employee));
  }

  @GetMapping("/salary/{id}")
  public ResponseEntity<EmployeePaycheckDTO> getEmployeeSalary(@PathVariable Long id) {
    final Employee employee = getEmployeeService.execute(id);
    return ResponseEntity.ok(new EmployeePaycheckDTO(employee));
  }

  @PutMapping()
  public ResponseEntity<EmployeeResponseDTO> updateEmployee(
      @Valid @RequestBody InputEmployeeDTO updatedEmployee) {
    final Employee employee = updateEmployeeService.execute(updatedEmployee);
    return ResponseEntity.ok(new EmployeeResponseDTO(employee));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    return ResponseEntity.ok(deleteEmployeeService.execute(id));
  }
}