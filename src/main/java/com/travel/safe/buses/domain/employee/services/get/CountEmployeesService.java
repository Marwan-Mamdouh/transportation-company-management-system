package com.travel.safe.buses.domain.employee.services.get;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
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