package com.travelSafe.buses.domain.employee.services.get;

import com.travelSafe.buses.comman.shared.Query;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import com.travelSafe.buses.domain.employee.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeService implements Query<Long, Employee> {

  private static final Logger logger = LoggerFactory.getLogger(GetEmployeeService.class);
  private final EmployeeRepository employeeRepository;

  public GetEmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee execute(Long input) {
    logger.info("Executing: {} with input:{}", getClass(), input);
    return employeeRepository.findById(input).orElseThrow(EmployeeNotFoundException::new);
  }
}