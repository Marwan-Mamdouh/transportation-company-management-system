package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.dto.CreateDepartmentDto;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateDepartmentService implements
    Command<CreateDepartmentDto, DepartmentResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(CreateDepartmentService.class);
  private final DepartmentRepository departmentRepository;
  private final DepartmentMapper mapper;

  public CreateDepartmentService(DepartmentRepository departmentRepository,
      DepartmentMapper mapper) {
    this.departmentRepository = departmentRepository;
    this.mapper = mapper;
  }

  @Override
  public DepartmentResponseDTO execute(CreateDepartmentDto input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var savedDepartment = departmentRepository.save(mapper.toEntity(input));
    return mapper.entityToDto(savedDepartment);
  }
}