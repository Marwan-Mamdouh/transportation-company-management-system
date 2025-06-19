package com.travelSafe.buses.domain.employee.controller;

import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.employee.dto.EmployeePaycheckDTO;
import com.travelSafe.buses.domain.employee.dto.EmployeeResponseDTO;
import com.travelSafe.buses.domain.employee.services.get.CountEmployeesService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesByDepartmentIdService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesBySupervisorService;
import com.travelSafe.buses.domain.employee.services.get.GetEmployeesService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

  private final CountEmployeesService countEmployeesService;
  private final GetEmployeesService getEmployeesService;
  private final GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService;
  private final GetEmployeesBySupervisorService getEmployeesBySupervisorService;

  public EmployeesController(CountEmployeesService countEmployeesService,
      GetEmployeesService getEmployeesService,
      GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService,
      GetEmployeesBySupervisorService getEmployeesBySupervisorService) {
    this.countEmployeesService = countEmployeesService;
    this.getEmployeesService = getEmployeesService;
    this.getEmployeesByDepartmentIdService = getEmployeesByDepartmentIdService;
    this.getEmployeesBySupervisorService = getEmployeesBySupervisorService;
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