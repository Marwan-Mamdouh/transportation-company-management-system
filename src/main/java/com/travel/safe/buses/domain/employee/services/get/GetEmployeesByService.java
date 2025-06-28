package com.travel.safe.buses.domain.employee.services.get;

import static com.travel.safe.buses.domain.employee.EmployeeSpecification.hasDepartment;
import static com.travel.safe.buses.domain.employee.EmployeeSpecification.hasSupervisor;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.jpa.domain.Specification.where;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.employee.EmployeeMapper;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.DtoResponseI;
import com.travel.safe.buses.domain.employee.dto.EmployeesGroupedRequestDTO;
import com.travel.safe.buses.domain.employee.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeesByService implements
    Query<EmployeesGroupedRequestDTO, List<DtoResponseI>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEmployeesByService.class);
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper mapper;

  public GetEmployeesByService(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
    this.employeeRepository = employeeRepository;
    this.mapper = mapper;
  }

  @Override
  public List<DtoResponseI> execute(EmployeesGroupedRequestDTO input) {
    LOGGER.debug("Executing: {} with input: {}", getClass(), input);
    final List<Employee> employees = employeeRepository.findAll(
        where(hasDepartment(input.departmentId()).and(hasSupervisor(input.supervisorId()))));

    return employees.stream()
        .map(input.isPaycheck() ? mapper::payCheckDTOFromEmployee : mapper::responseDTOFromEmployee)
        .collect(toList());
  }
}