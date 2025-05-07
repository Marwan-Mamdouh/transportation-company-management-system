package com.travelSafe.buses.employee.controller;

import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.employee.model.DTO.EmployeeDTO;
import com.travelSafe.buses.employee.model.DTO.EmployeePaycheckDTO;
import com.travelSafe.buses.employee.model.DTO.UpdateEmployeeDTO;
import com.travelSafe.buses.employee.services.CreateEmployeeService;
import com.travelSafe.buses.employee.services.DeleteEmployeeService;
import com.travelSafe.buses.employee.services.UpdateEmployeeService;
import com.travelSafe.buses.employee.services.get.GetEmployeeService;
import com.travelSafe.buses.employee.services.get.GetEmployeesByDepartmentIdService;
import com.travelSafe.buses.employee.services.get.GetEmployeesBySupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
      GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService,
      GetEmployeesBySupervisorService getEmployeesBySupervisorService,
      UpdateEmployeeService updateEmployeeService) {
    this.createEmployeeService = createEmployeeService;
    this.deleteEmployeeService = deleteEmployeeService;
    this.getEmployeeService = getEmployeeService;
    this.updateEmployeeService = updateEmployeeService;
  }

  @PostMapping
  public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee) {
    final Employee savedEmployee = createEmployeeService.execute(employee);
    return ResponseEntity.ok(new EmployeeDTO(savedEmployee));
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
    final Employee employee = getEmployeeService.execute(id);
    return ResponseEntity.ok(new EmployeeDTO(employee));
  }

  @GetMapping("/salary/{id}")
  public ResponseEntity<EmployeePaycheckDTO> getEmployeeSalary(@PathVariable Long id) {
    final Employee employee = getEmployeeService.execute(id);
    return ResponseEntity.ok(new EmployeePaycheckDTO(employee));
  }

  @PutMapping()
  public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Long empId,
      @RequestBody Employee updatedEmployee) {
    final Employee employee = updateEmployeeService.execute(
        new UpdateEmployeeDTO(empId, updatedEmployee));
    return ResponseEntity.ok(new EmployeeDTO(employee));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    return ResponseEntity.ok(deleteEmployeeService.execute(id));
  }
}