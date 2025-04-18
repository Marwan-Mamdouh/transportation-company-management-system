package com.travelsave.buses.employee.services.update;

import com.travelsave.buses.Command;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.employee.model.UpdateEmployeePart;
import com.travelsave.buses.exceptions.employee.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeFirstnameService implements Command<UpdateEmployeePart, EmployeeDTO> {

  private final EmployeeRepository employeeRepository;

  public UpdateEmployeeFirstnameService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<EmployeeDTO> execute(UpdateEmployeePart input) {
    final Employee employee = employeeRepository.findById(input.id())
        .orElseThrow(EmployeeNotFoundException::new);

    employee.setFirstName(input.employeeProperty());
    final Employee savedEmployee = employeeRepository.save(employee);
    return ResponseEntity.accepted().body(new EmployeeDTO(savedEmployee));
  }
}