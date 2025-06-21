package com.travelSafe.buses.domain.employee.controller;

import com.travelSafe.buses.domain.employee.dto.EmployeePaycheckDTO;
import com.travelSafe.buses.domain.employee.dto.EmployeeResponseDTO;
import com.travelSafe.buses.domain.employee.dto.InputEmployeeDTO;
import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.services.DeleteEmployeeService;
import com.travelSafe.buses.domain.employee.services.UpdateEmployeeService;
import com.travelSafe.buses.domain.employee.services.get.CountEmployeesService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeeService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesByDepartmentIdService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesBySupervisorService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final CountEmployeesService countEmployeesService;
  private final GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService;
  private final GetEmployeesBySupervisorService getEmployeesBySupervisorService;
  private final GetEmployeeService getEmployeeService;
  private final GetEmployeesService getEmployeesService;

  private final DeleteEmployeeService deleteEmployeeService;

  private final UpdateEmployeeService updateEmployeeService;


  public EmployeeController(DeleteEmployeeService deleteEmployeeService,
      GetEmployeeService getEmployeeService, UpdateEmployeeService updateEmployeeService,
      CountEmployeesService countEmployeesService, GetEmployeesService getEmployeesService,
      GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService,
      GetEmployeesBySupervisorService getEmployeesBySupervisorService) {
    this.deleteEmployeeService = deleteEmployeeService;
    this.getEmployeeService = getEmployeeService;
    this.updateEmployeeService = updateEmployeeService;
    this.countEmployeesService = countEmployeesService;
    this.getEmployeesService = getEmployeesService;
    this.getEmployeesByDepartmentIdService = getEmployeesByDepartmentIdService;
    this.getEmployeesBySupervisorService = getEmployeesBySupervisorService;
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

  @GetMapping("/count")
  public ResponseEntity<Long> countEmployees() {
    return ResponseEntity.ok(countEmployeesService.execute(null));
  }

  @GetMapping()
  public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
    final List<Employee> employees = getEmployeesService.execute(null);
    return ResponseEntity.ok(employees.stream().map(EmployeeResponseDTO::new).toList());
  }

  @GetMapping("/salaries")
  public ResponseEntity<List<EmployeePaycheckDTO>> getSalaries() {
    final List<Employee> employees = getEmployeesService.execute(null);
    return ResponseEntity.ok(employees.stream().map(EmployeePaycheckDTO::new).toList());
  }

  @GetMapping("/by/department")
  public ResponseEntity<List<EmployeeResponseDTO>> getEmployeeByDepartment(
      @RequestParam Integer id) {
    final List<Employee> employees = getEmployeesByDepartmentIdService.execute(id);
    return ResponseEntity.ok(employees.stream().map(EmployeeResponseDTO::new).toList());
  }

  @GetMapping("/by/supervisor")
  public ResponseEntity<List<EmployeeResponseDTO>> getEmployeeBySupervisor(@RequestParam Long id) {
    final List<Employee> employees = getEmployeesBySupervisorService.execute(id);
    return ResponseEntity.ok(employees.stream().map(EmployeeResponseDTO::new).toList());
  }
}