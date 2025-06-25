package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.dto.PromoteManagerDTO;
import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.model.Department;
import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PromoteManagerService implements Command<PromoteManagerDTO, DepartmentResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(PromoteManagerService.class);
  private final DepartmentMapper mapper;
  private final GetEmployeeService getEmployeeService;
  private final GetDepartmentService getDepartmentService;
  private final DepartmentRepository departmentRepository;

  public PromoteManagerService(DepartmentRepository departmentRepository, DepartmentMapper mapper,
      GetEmployeeService getEmployeeService, GetDepartmentService getDepartmentService) {
    this.mapper = mapper;
    this.getEmployeeService = getEmployeeService;
    this.departmentRepository = departmentRepository;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  @Transactional
  public DepartmentResponseDTO execute(PromoteManagerDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    // check
    final Employee foundEmployee = getEmployeeService.execute(input.managerId());
    final Department foundDepartment = mapper.responseDtoToEntity(
        getDepartmentService.execute(input.departmentId()));
    // save
    foundDepartment.setDepartmentManager(foundEmployee);
    // return
    return mapper.entityToDto(departmentRepository.save(foundDepartment));
  }
}