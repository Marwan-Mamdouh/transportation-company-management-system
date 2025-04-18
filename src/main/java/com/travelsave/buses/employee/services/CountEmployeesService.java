package com.travelsave.buses.employee.services;

import com.travelsave.buses.Query;
import com.travelsave.buses.employee.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CountEmployeesService implements Query<Void, Long> {

  private final EmployeeRepository employeeRepository;

  public CountEmployeesService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public ResponseEntity<Long> execute(Void input) {
    return ResponseEntity.ok(employeeRepository.count());
  }
}