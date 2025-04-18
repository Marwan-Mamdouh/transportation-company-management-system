package com.travelsave.buses.employee;

import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.services.CountEmployeesService;
import com.travelsave.buses.employee.services.CreateEmployeeService;
import com.travelsave.buses.employee.services.DeleteEmployeeService;
import com.travelsave.buses.employee.services.get.GetEmployeeService;
import com.travelsave.buses.employee.services.get.GetEmployeesService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final CreateEmployeeService createEmployeeService;

  private final DeleteEmployeeService deleteEmployeeService;

  private final GetEmployeeService getEmployeeService;
  private final GetEmployeesService getEmployeesService;
  private final CountEmployeesService countEmployeesService;

  public EmployeeController(CreateEmployeeService createEmployeeService,
      DeleteEmployeeService deleteEmployeeService, GetEmployeeService getEmployeeService,
      GetEmployeesService getEmployeesService, CountEmployeesService countEmployeesService) {
    this.createEmployeeService = createEmployeeService;
    this.deleteEmployeeService = deleteEmployeeService;
    this.getEmployeeService = getEmployeeService;
    this.getEmployeesService = getEmployeesService;
    this.countEmployeesService = countEmployeesService;
  }

  @PostMapping("/employee")
  public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee) {
    return createEmployeeService.execute(employee);
  }

  @GetMapping("/employee/{id}")
  public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable String id) {
    return getEmployeeService.execute(id);
  }

  @GetMapping("/employees")
  public ResponseEntity<List<EmployeeDTO>> getEmployees() {
    return getEmployeesService.execute(null);
  }

  @GetMapping("/employees/count")
  public ResponseEntity<Long> countEmployees() {
    return countEmployeesService.execute(null);
  }

  @DeleteMapping("/employee/{id}")
  public ResponseEntity<Void> createEmployee(@PathVariable String id) {
    return deleteEmployeeService.execute(id);
  }
}