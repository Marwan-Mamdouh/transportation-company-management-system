package com.travelSafe.buses.department.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.department.DepartmentRepository;
import com.travelSafe.buses.department.model.DTO.UpdatedDepartmentDTO;
import com.travelSafe.buses.department.model.Department;
import com.travelSafe.buses.department.model.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateDepartmentService implements Command<UpdatedDepartmentDTO, Department> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateDepartmentService.class);
  private final DepartmentMapper departmentMapper;
  private final DepartmentRepository departmentRepository;
  private final GetDepartmentService getDepartmentService;

  public UpdateDepartmentService(DepartmentRepository departmentRepository,
      DepartmentMapper departmentMapper, GetDepartmentService getDepartmentService) {
    this.departmentMapper = departmentMapper;
    this.departmentRepository = departmentRepository;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  public Department execute(UpdatedDepartmentDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    // check
    getDepartmentService.execute(input.id());
    // save & return
    return departmentRepository.save(departmentMapper.toEntity(input));
  }
}