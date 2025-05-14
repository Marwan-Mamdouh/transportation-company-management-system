package com.travelSafe.buses.domain.employee.services.get;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.domain.department.service.GetDepartmentService;
import com.travelSafe.buses.domain.employee.EmployeeRepository;
import com.travelSafe.buses.domain.employee.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesByDepartmentIdService implements Query<Integer, List<Employee>> {

  private static final Logger logger = LoggerFactory.getLogger(
      GetEmployeesByDepartmentIdService.class);
  private final EmployeeRepository employeeRepository;
  private final GetDepartmentService getDepartmentService;

  public GetEmployeesByDepartmentIdService(EmployeeRepository employeeRepository,
      GetDepartmentService getDepartmentService) {
    this.employeeRepository = employeeRepository;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  public List<Employee> execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    getDepartmentService.execute(input);
    return employeeRepository.findByDepartmentId(input);
  }
}