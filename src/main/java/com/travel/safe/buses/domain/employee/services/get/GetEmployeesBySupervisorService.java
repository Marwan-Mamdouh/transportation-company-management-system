package com.travel.safe.buses.domain.employee.services.get;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesBySupervisorService implements Query<Long, List<Employee>> {

  private static final Logger logger = LoggerFactory.getLogger(
      GetEmployeesBySupervisorService.class);
  private final EmployeeRepository employeeRepository;

  public GetEmployeesBySupervisorService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> execute(Long input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    return employeeRepository.findBySupervisor_EmployeeId(input);
  }
}