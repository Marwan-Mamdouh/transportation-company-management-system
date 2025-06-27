package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.dto.UpdatedDepartmentDTO;
import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateDepartmentService implements
    Command<UpdatedDepartmentDTO, DepartmentResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateDepartmentService.class);
  private final DepartmentMapper mapper;
  private final DepartmentRepository departmentRepository;
  private final GetDepartmentService getDepartmentService;

  public UpdateDepartmentService(DepartmentRepository departmentRepository,
      DepartmentMapper departmentMapper, GetDepartmentService getDepartmentService) {
    this.mapper = departmentMapper;
    this.departmentRepository = departmentRepository;
    this.getDepartmentService = getDepartmentService;
  }

  @Override
  public DepartmentResponseDTO execute(UpdatedDepartmentDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    getDepartmentService.execute(input.id());
    final var savedDepartment = departmentRepository.save(mapper.toEntity(input));
    return mapper.entityToDto(savedDepartment);
  }
}