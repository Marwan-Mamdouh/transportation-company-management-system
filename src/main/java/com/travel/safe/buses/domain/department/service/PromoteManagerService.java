package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.dto.PromoteManagerDTO;
import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PromoteManagerService implements Command<PromoteManagerDTO, DepartmentResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(PromoteManagerService.class);
  private final DepartmentMapper mapper;
  private final EmployeeRepository employeeRepository;
  private final GetDepartmentService getDepartmentService;
  private final DepartmentRepository departmentRepository;

  public PromoteManagerService(DepartmentRepository departmentRepository, DepartmentMapper mapper,
      EmployeeRepository employeeRepository, GetDepartmentService getDepartmentService) {
    this.mapper = mapper;
    this.employeeRepository = employeeRepository;
    this.departmentRepository = departmentRepository;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  @Transactional
  public DepartmentResponseDTO execute(PromoteManagerDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    // check
    final var foundEmployee = employeeRepository.findById(input.managerId())
        .orElseThrow(EmployeeNotFoundException::new);
    final Department foundDepartment = mapper.responseDtoToEntity(
        getDepartmentService.execute(input.departmentId()));
    // save
    foundDepartment.setDepartmentManager(foundEmployee);
    // return
    return mapper.entityToDto(departmentRepository.save(foundDepartment));
  }
}