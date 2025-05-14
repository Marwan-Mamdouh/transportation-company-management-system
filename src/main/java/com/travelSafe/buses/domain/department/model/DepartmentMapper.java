package com.travelSafe.buses.domain.department.model;

import com.travelSafe.buses.domain.department.model.DTO.CreateDepartmentDto;
import com.travelSafe.buses.domain.department.model.DTO.DepartmentResponseDTO;
import com.travelSafe.buses.domain.department.model.DTO.UpdatedDepartmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

//  DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

  Department toEntity(CreateDepartmentDto department);

  Department toEntity(UpdatedDepartmentDTO department);

  DepartmentResponseDTO toBaseDto(Department department);
}