package com.travelSafe.buses.employee.services.get;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.employee.EmployeeRepository;
import com.travelSafe.buses.employee.model.Employee;
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
    logger.info("Executing: {} with input: {}", getClass(), input);
    return employeeRepository.findBySupervisor_EmployeeId(input);
  }
}