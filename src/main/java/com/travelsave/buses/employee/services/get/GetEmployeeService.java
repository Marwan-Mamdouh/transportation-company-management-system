package com.travelsave.buses.employee.services.get;

import com.travelsave.buses.Query;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.employee.model.EmployeeDTO;
import com.travelsave.buses.exceptions.employee.EmployeeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeService implements Query<String, EmployeeDTO> {

  private final EmployeeRepository employeeRepository;

  public GetEmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public ResponseEntity<EmployeeDTO> execute(String input) {
    final var emp = employeeRepository.findById(input).orElseThrow(EmployeeNotFoundException::new);
    return ResponseEntity.ok(new EmployeeDTO(emp));
  }
}
