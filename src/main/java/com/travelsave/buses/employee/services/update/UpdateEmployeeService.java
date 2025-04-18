package com.travelsave.buses.employee.services.update;

import com.travelsave.buses.Command;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.model.UpdateEmployeeCommand;
import com.travelsave.buses.exceptions.employee.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeService implements Command<UpdateEmployeeCommand, EmployeeDTO> {

  private final EmployeeRepository employeeRepository;

  public UpdateEmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<EmployeeDTO> execute(UpdateEmployeeCommand command) {
    final String id = command.id();
    employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    final Employee employee = command.updateedEmployee();
    employee.setEmployeeId(id);
    // validate Employee before save it to db
    final Employee savedEmployee = employeeRepository.save(employee);
    return ResponseEntity.ok(new EmployeeDTO(savedEmployee));
  }
}