package com.travel.safe.buses.domain.employee;

import com.travel.safe.buses.domain.employee.dto.CreateEmployeeDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeeLoginDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeePaycheckDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeeResponseDTO;
import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.services.CreateEmployeeService;
import com.travel.safe.buses.domain.employee.services.DeleteEmployeeService;
import com.travel.safe.buses.domain.employee.services.EmployeeLoginService;
import com.travel.safe.buses.domain.employee.services.UpdateEmployeeService;
import com.travel.safe.buses.domain.employee.services.get.CountEmployeesService;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeeService;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeesByDepartmentIdService;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeesBySupervisorService;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeesService;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeMapper mapper;

  private final CountEmployeesService countEmployeesService;
  private final GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService;
  private final GetEmployeesBySupervisorService getEmployeesBySupervisorService;
  private final GetEmployeeService getEmployeeService;
  private final GetEmployeesService getEmployeesService;

  private final EmployeeLoginService employeeLoginService;
  private final CreateEmployeeService createEmployeeService;

  private final DeleteEmployeeService deleteEmployeeService;

  private final UpdateEmployeeService updateEmployeeService;


  public EmployeeController(EmployeeMapper mapper, DeleteEmployeeService deleteEmployeeService,
      GetEmployeeService getEmployeeService, UpdateEmployeeService updateEmployeeService,
      CountEmployeesService countEmployeesService, GetEmployeesService getEmployeesService,
      GetEmployeesByDepartmentIdService getEmployeesByDepartmentIdService,
      GetEmployeesBySupervisorService getEmployeesBySupervisorService,
      EmployeeLoginService employeeLoginService, CreateEmployeeService createEmployeeService) {
    this.mapper = mapper;
    this.deleteEmployeeService = deleteEmployeeService;
    this.getEmployeeService = getEmployeeService;
    this.updateEmployeeService = updateEmployeeService;
    this.countEmployeesService = countEmployeesService;
    this.getEmployeesService = getEmployeesService;
    this.getEmployeesByDepartmentIdService = getEmployeesByDepartmentIdService;
    this.getEmployeesBySupervisorService = getEmployeesBySupervisorService;
    this.employeeLoginService = employeeLoginService;
    this.createEmployeeService = createEmployeeService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
    final Employee employee = getEmployeeService.execute(id);
    return ResponseEntity.ok(mapper.responseDTOFromEmployee(employee));
  }

  @GetMapping("/salary/{id}")
  public ResponseEntity<EmployeePaycheckDTO> getEmployeeSalary(@PathVariable Long id) {
    final Employee employee = getEmployeeService.execute(id);
    return ResponseEntity.ok(mapper.payCheckDTOFromEmployee(employee));
  }

  @PutMapping()
  public ResponseEntity<EmployeeResponseDTO> updateEmployee(
      @Valid @RequestBody CreateEmployeeDTO updatedEmployee) {
    final Employee employee = updateEmployeeService.execute(updatedEmployee);
    return ResponseEntity.ok(mapper.responseDTOFromEmployee(employee));
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
    return ResponseEntity.ok(employees.stream().map(mapper::responseDTOFromEmployee).toList());
  }

  @GetMapping("/salaries")
  public ResponseEntity<List<EmployeePaycheckDTO>> getSalaries() {
    final List<Employee> employees = getEmployeesService.execute(null);
    return ResponseEntity.ok(employees.stream().map(mapper::payCheckDTOFromEmployee).toList());
  }

  @GetMapping("/by/department")
  public ResponseEntity<List<EmployeeResponseDTO>> getEmployeeByDepartment(
      @RequestParam Integer id) {
    final List<Employee> employees = getEmployeesByDepartmentIdService.execute(id);
    return ResponseEntity.ok(employees.stream().map(mapper::responseDTOFromEmployee).toList());
  }

  @GetMapping("/by/supervisor")
  public ResponseEntity<List<EmployeeResponseDTO>> getEmployeeBySupervisor(@RequestParam Long id) {
    final List<Employee> employees = getEmployeesBySupervisorService.execute(id);
    return ResponseEntity.ok(employees.stream().map(mapper::responseDTOFromEmployee).toList());
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody EmployeeLoginDTO empLoginCre) {
    return ResponseEntity.ok(employeeLoginService.execute(empLoginCre));
  }

  @PostMapping("/register")
  public ResponseEntity<EmployeeResponseDTO> createNewEmployee(
      @Valid @RequestBody CreateEmployeeDTO empLoginCre) {
    return ResponseEntity.ok(
        mapper.responseDTOFromEmployee(createEmployeeService.execute(empLoginCre)));
  }

//  @PostMapping("/refresh")
//  public ResponseEntity<String> refresh(@RequestBody EmployeeLoginDTO empLoginCre) {
//    return null;
//  }
//
//  @PostMapping("/logout")
//  public ResponseEntity<String> logout(@RequestBody EmployeeLoginDTO empLoginCre) {
//    return null;
//  }
}