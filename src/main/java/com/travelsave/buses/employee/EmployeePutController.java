package com.travelsave.buses.employee;

import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.model.UpdateEmployeeCommand;
import com.travelsave.buses.employee.model.UpdateEmployeeDepartmentCommand;
import com.travelsave.buses.employee.model.UpdateEmployeePart;
import com.travelsave.buses.employee.services.update.UpdateEmployeeDepartmentService;
import com.travelsave.buses.employee.services.update.UpdateEmployeeEmailService;
import com.travelsave.buses.employee.services.update.UpdateEmployeeFirstnameService;
import com.travelsave.buses.employee.services.update.UpdateEmployeeLastNameService;
import com.travelsave.buses.employee.services.update.UpdateEmployeePhoneNumberService;
import com.travelsave.buses.employee.services.update.UpdateEmployeeService;
import com.travelsave.buses.employee.services.update.UpdateEmployeeSupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/employee")
public class EmployeePutController {

  private final UpdateEmployeeService updateEmployeeService;
  private final UpdateEmployeeFirstnameService updateEmployeeFirstnameService;
  private final UpdateEmployeeLastNameService updateEmployeeLastNameService;
  private final UpdateEmployeePhoneNumberService updateEmployeePhoneNumberService;
  private final UpdateEmployeeEmailService updateEmployeeEmailService;
  private final UpdateEmployeeDepartmentService updateEmployeeDepartmentService;
  private final UpdateEmployeeSupervisorService updateEmployeeSupervisorService;

  public EmployeePutController(UpdateEmployeeService updateEmployeeService,
      UpdateEmployeeFirstnameService updateEmployeeFirstnameService,
      UpdateEmployeeLastNameService updateEmployeeLastNameService,
      UpdateEmployeePhoneNumberService updateEmployeePhoneNumberService,
      UpdateEmployeeEmailService updateEmployeeEmailService,
      UpdateEmployeeDepartmentService updateEmployeeDepartmentService,
      UpdateEmployeeSupervisorService updateEmployeeSupervisorService) {
    this.updateEmployeeService = updateEmployeeService;
    this.updateEmployeeFirstnameService = updateEmployeeFirstnameService;
    this.updateEmployeeLastNameService = updateEmployeeLastNameService;
    this.updateEmployeePhoneNumberService = updateEmployeePhoneNumberService;
    this.updateEmployeeEmailService = updateEmployeeEmailService;
    this.updateEmployeeDepartmentService = updateEmployeeDepartmentService;
    this.updateEmployeeSupervisorService = updateEmployeeSupervisorService;
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id,
      @RequestBody Employee employee) {
    return updateEmployeeService.execute(new UpdateEmployeeCommand(id, employee));
  }

  @PutMapping("/firstname")
  public ResponseEntity<EmployeeDTO> updateEmployeeFirstname(@RequestParam String id,
      @RequestParam String supervisorId) {
    return updateEmployeeFirstnameService.execute(new UpdateEmployeePart(id, supervisorId));
  }

  @PutMapping("/lastname")
  public ResponseEntity<EmployeeDTO> updateEmployeeLastname(@PathVariable String id,
      @RequestParam String lastname) {
    return updateEmployeeLastNameService.execute(new UpdateEmployeePart(id, lastname));
  }

  @PutMapping("/supervisor/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployeeSupervisor(@PathVariable String id,
      @RequestParam String supervisor) {
    return updateEmployeeSupervisorService.execute(new UpdateEmployeePart(id, supervisor));
  }

  @PutMapping("/department/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployeeDepartment(@PathVariable String id,
      @RequestParam Integer departmentId) {
    return updateEmployeeDepartmentService.execute(
        new UpdateEmployeeDepartmentCommand(id, departmentId));
  }

  @PutMapping("/email/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployeeEmail(@PathVariable String id,
      @RequestParam String email) {
    return updateEmployeeEmailService.execute(new UpdateEmployeePart(id, email));
  }

  @PutMapping("/phone/number/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployeePhoneNumber(@PathVariable String id,
      @RequestParam String phoneNumber) {
    return updateEmployeePhoneNumberService.execute(new UpdateEmployeePart(id, phoneNumber));
  }
}