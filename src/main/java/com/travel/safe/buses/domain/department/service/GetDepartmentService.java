package com.travel.safe.buses.domain.department.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.DepartmentMapper;
import com.travel.safe.buses.domain.department.DepartmentRepository;
import com.travel.safe.buses.domain.department.exception.DepartmentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentService implements Query<Integer, DepartmentResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(GetDepartmentService.class);
  private final DepartmentRepository departmentRepository;
  private final DepartmentMapper mapper;

  public GetDepartmentService(DepartmentRepository departmentRepository, DepartmentMapper mapper) {
    this.departmentRepository = departmentRepository;
    this.mapper = mapper;
  }

  @Override
  public DepartmentResponseDTO execute(Integer input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var foundDepartment = departmentRepository.findById(input)
        .orElseThrow(DepartmentNotFoundException::new);
    return mapper.entityToDto(foundDepartment);
  }
}