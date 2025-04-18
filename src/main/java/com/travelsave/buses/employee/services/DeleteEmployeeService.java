package com.travelsave.buses.employee.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.employee.EmployeeRepository;
import com.travelsave.buses.exceptions.employee.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeService implements Command<String, Void> {

  private final EmployeeRepository employeeRepository;

  public DeleteEmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public ResponseEntity<Void> execute(String input) {
    final var emp = employeeRepository.findById(input).orElseThrow(EmployeeNotFoundException::new);
    employeeRepository.delete(emp);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}