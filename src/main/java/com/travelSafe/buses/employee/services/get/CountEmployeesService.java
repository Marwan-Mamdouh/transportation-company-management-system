package com.travelSafe.buses.employee.services.get;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CountEmployeesService implements Query<Void, Long> {

  private final EmployeeRepository employeeRepository;

  private static final Logger logger = LoggerFactory.getLogger(CountEmployeesService.class);

  public CountEmployeesService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Long execute(Void input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return employeeRepository.count();
  }
}