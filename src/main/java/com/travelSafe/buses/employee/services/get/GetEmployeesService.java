package com.travelSafe.buses.employee.services.get;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.employee.EmployeeRepository;
import com.travelSafe.buses.employee.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesService implements Query<Void, List<Employee>> {

  private final EmployeeRepository employeeRepository;

  private static final Logger logger = LoggerFactory.getLogger(GetEmployeesService.class);

  public GetEmployeesService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> execute(Void input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return employeeRepository.findAll();
  }
}
