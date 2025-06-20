package com.travelSafe.buses.domain.employee.controller;

import com.travelSafe.buses.domain.employee.dto.EmployeePaycheckDTO;
import com.travelSafe.buses.domain.employee.dto.EmployeeResponseDTO;
import com.travelSafe.buses.domain.employee.dto.InputEmployeeDTO;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.services.DeleteEmployeeService;
import com.travelSafe.buses.domain.employee.services.UpdateEmployeeService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

  private final DeleteEmployeeService deleteEmployeeService;

  private final GetEmployeeService getEmployeeService;

  private final UpdateEmployeeService updateEmployeeService;

  public EmployeeController(DeleteEmployeeService deleteEmployeeService,
      GetEmployeeService getEmployeeService, UpdateEmployeeService updateEmployeeService) {
    this.deleteEmployeeService = deleteEmployeeService;
    this.getEmployeeService = getEmployeeService;
    this.updateEmployeeService = updateEmployeeService;
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