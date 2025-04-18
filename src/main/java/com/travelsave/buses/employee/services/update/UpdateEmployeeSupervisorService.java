package com.travelsave.buses.employee.services.update;

import com.travelsave.buses.Command;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.model.UpdateEmployeePart;
import com.travelsave.buses.exceptions.employee.EmployeeNotFoundException;
import com.travelsave.buses.exceptions.employee.SupervisorNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeSupervisorService implements Command<UpdateEmployeePart, EmployeeDTO> {

  private final EmployeeRepository employeeRepository;

  public UpdateEmployeeSupervisorService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<EmployeeDTO> execute(UpdateEmployeePart input) {
    final Employee employee = employeeRepository.findById(input.id())
        .orElseThrow(EmployeeNotFoundException::new);
    final Employee supervisor = employeeRepository.findById(input.employeeProperty())
        .orElseThrow(SupervisorNotFoundException::new);

    employee.setSupervisor(supervisor);
    final Employee savedEmployee = employeeRepository.save(employee);
    return ResponseEntity.ok(new EmployeeDTO(savedEmployee));
  }
}