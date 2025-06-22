package com.travel.safe.buses.domain.employee.services.get;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesService implements Query<Void, List<Employee>> {

  private static final Logger logger = LoggerFactory.getLogger(GetEmployeesService.class);
  private final EmployeeRepository employeeRepository;

  public GetEmployeesService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> execute(Void input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    return employeeRepository.findAll();
  }
}
