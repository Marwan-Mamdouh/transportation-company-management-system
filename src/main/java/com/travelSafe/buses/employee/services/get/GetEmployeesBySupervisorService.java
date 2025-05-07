package com.travelSafe.buses.employee.services.get;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.employee.EmployeeRepository;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.exceptions.employee.SupervisorNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesBySupervisorService implements Query<Long, List<Employee>> {

  private final EmployeeRepository employeeRepository;
  private final GetEmployeeService getEmployeeService;

  private static final Logger logger = LoggerFactory.getLogger(
      GetEmployeesBySupervisorService.class);

  public GetEmployeesBySupervisorService(EmployeeRepository employeeRepository,
      GetEmployeeService getEmployeeService) {
    this.employeeRepository = employeeRepository;
    this.getEmployeeService = getEmployeeService;
  }

  @Override
  public List<Employee> execute(Long input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
//    getEmployeeService.execute(input);

    if (employeeRepository.existsBySupervisor_EmployeeId(input)) {
      return employeeRepository.findBySupervisor_EmployeeId(input);
    }
    throw new SupervisorNotFoundException();
  }
}