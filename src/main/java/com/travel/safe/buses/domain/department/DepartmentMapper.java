package com.travel.safe.buses.domain.department;

import com.travel.safe.buses.domain.department.DTO.CreateDepartmentDto;
import com.travel.safe.buses.domain.department.DTO.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.DTO.UpdatedDepartmentDTO;
import com.travel.safe.buses.domain.department.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

//  DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

  Department toEntity(CreateDepartmentDto department);

  Department toEntity(UpdatedDepartmentDTO department);

  DepartmentResponseDTO toBaseDto(Department department);
}