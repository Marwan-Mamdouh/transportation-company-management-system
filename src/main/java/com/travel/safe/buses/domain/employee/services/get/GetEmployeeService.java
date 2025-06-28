package com.travel.safe.buses.domain.employee.services.get;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.employee.EmployeeMapper;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.dto.DtoResponseI;
import com.travel.safe.buses.domain.employee.dto.EmployeeRequestDTO;
import com.travel.safe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import com.travel.safe.buses.domain.employee.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeService implements Query<EmployeeRequestDTO, DtoResponseI> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEmployeeService.class);
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper mapper;

  public GetEmployeeService(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
    this.employeeRepository = employeeRepository;
    this.mapper = mapper;
  }

  @Override
  public DtoResponseI execute(EmployeeRequestDTO input) {
    LOGGER.debug("Executing: {} with input:{}", getClass(), input);
    final Employee employee = employeeRepository.findById(input.id())
        .orElseThrow(EmployeeNotFoundException::new);
    if (input.paycheck()) {
      return mapper.payCheckDTOFromEmployee(employee);
    } else {
      return mapper.responseDTOFromEmployee(employee);
    }
  }
}