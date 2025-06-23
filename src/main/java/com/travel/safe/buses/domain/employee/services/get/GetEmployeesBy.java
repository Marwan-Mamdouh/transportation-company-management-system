package com.travel.safe.buses.domain.employee.services.get;

import static com.travel.safe.buses.domain.employee.EmployeeSpecification.hasDepartment;
import static com.travel.safe.buses.domain.employee.EmployeeSpecification.hasSupervisor;
import static org.springframework.data.jpa.domain.Specification.where;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.EmployeeSpecificationDTO;
import com.travel.safe.buses.domain.employee.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesBy implements Query<EmployeeSpecificationDTO, List<Employee>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEmployeesBy.class);
  private final EmployeeRepository employeeRepository;

  public GetEmployeesBy(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> execute(EmployeeSpecificationDTO input) {
    LOGGER.debug("Executing: {} with input: {}", getClass(), input);
    return employeeRepository.findAll(
        where(hasDepartment(input.departmentId()).and(hasSupervisor(input.supervisorId()))));
  }
}