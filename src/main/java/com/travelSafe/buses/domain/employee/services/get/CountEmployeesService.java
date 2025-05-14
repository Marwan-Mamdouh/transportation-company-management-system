package com.travelSafe.buses.domin.employee.services.get;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.domin.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CountEmployeesService implements Query<Void, Long> {

  private static final Logger logger = LoggerFactory.getLogger(CountEmployeesService.class);
  private final EmployeeRepository employeeRepository;

  public CountEmployeesService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Long execute(Void input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return employeeRepository.count();
  }
}