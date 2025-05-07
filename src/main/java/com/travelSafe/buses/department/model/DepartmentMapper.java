package com.travelSafe.buses.department.model;

import com.travelSafe.buses.department.model.DTO.CreateDepartmentDto;
import com.travelSafe.buses.department.model.DTO.DepartmentDTO;
import com.travelSafe.buses.department.model.DTO.UpdatedDepartmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

//  DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

  Department toEntity(CreateDepartmentDto department);

  Department toEntity(UpdatedDepartmentDTO department);

  DepartmentDTO toBaseDto(Department department);
}