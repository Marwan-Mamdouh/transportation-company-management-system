package com.travel.safe.buses.domain.employee;

import com.travel.safe.buses.domain.employee.dto.CreateEmployeeDTO;
import com.travel.safe.buses.domain.employee.dto.DtoResponseI;
import com.travel.safe.buses.domain.employee.dto.EmployeeLoginDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeeResponseDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeesGroupedRequestDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeeRequestDTO;
import com.travel.safe.buses.domain.employee.services.CreateEmployeeService;
import com.travel.safe.buses.domain.employee.services.DeleteEmployeeService;
import com.travel.safe.buses.domain.employee.services.EmployeeLoginService;
import com.travel.safe.buses.domain.employee.services.UpdateEmployeeService;
import com.travel.safe.buses.domain.employee.services.get.CountEmployeesService;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeeService;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeesBy;
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

  private final CountEmployeesService countEmployeesService;
  private final GetEmployeesBy getEmployeesBy;
  private final GetEmployeeService getEmployeeService;

  private final EmployeeLoginService employeeLoginService;
  private final CreateEmployeeService createEmployeeService;

  private final DeleteEmployeeService deleteEmployeeService;

  private final UpdateEmployeeService updateEmployeeService;


  public EmployeeController(DeleteEmployeeService deleteEmployeeService,
      GetEmployeeService getEmployeeService, UpdateEmployeeService updateEmployeeService,
      CountEmployeesService countEmployeesService, GetEmployeesBy getEmployeesBySupervisorService,
      EmployeeLoginService employeeLoginService, CreateEmployeeService createEmployeeService) {
    this.deleteEmployeeService = deleteEmployeeService;
    this.getEmployeeService = getEmployeeService;
    this.updateEmployeeService = updateEmployeeService;
    this.countEmployeesService = countEmployeesService;
    this.getEmployeesBy = getEmployeesBySupervisorService;
    this.employeeLoginService = employeeLoginService;
    this.createEmployeeService = createEmployeeService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<DtoResponseI> getEmployee(@PathVariable Long id,
      @RequestParam(name = "paycheck", required = false, defaultValue = "false") boolean paycheck) {
    return ResponseEntity.ok(getEmployeeService.execute(new EmployeeRequestDTO(id, paycheck)));
  }

  @PutMapping
  public ResponseEntity<EmployeeResponseDTO> updateEmployee(
      @Valid @RequestBody CreateEmployeeDTO updatedEmployee) {
    return ResponseEntity.ok(updateEmployeeService.execute(updatedEmployee));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    return ResponseEntity.ok(deleteEmployeeService.execute(id));
  }

  @GetMapping("/count")
  public ResponseEntity<Long> countEmployees() {
    return ResponseEntity.ok(countEmployeesService.execute(null));
  }

  @GetMapping
  public ResponseEntity<List<DtoResponseI>> getEmployees(
      @RequestParam(required = false) Long supervisorId,
      @RequestParam(required = false) Integer departmentId,
      @RequestParam(name = "paycheck", required = false, defaultValue = "false") boolean isPaycheck) {
    final List<DtoResponseI> employees = getEmployeesBy.execute(
        new EmployeesGroupedRequestDTO(supervisorId, departmentId, isPaycheck));
    return ResponseEntity.ok(employees);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody EmployeeLoginDTO empLoginCre) {
    return ResponseEntity.ok(employeeLoginService.execute(empLoginCre));
  }

  @PostMapping("/register")
  public ResponseEntity<EmployeeResponseDTO> createNewEmployee(
      @Valid @RequestBody CreateEmployeeDTO empLoginCre) {
    return ResponseEntity.ok(createEmployeeService.execute(empLoginCre));
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