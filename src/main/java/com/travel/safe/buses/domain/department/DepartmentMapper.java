package com.travel.safe.buses.domain.department;

import com.travel.safe.buses.domain.department.DTO.CreateDepartmentDto;
import com.travel.safe.buses.domain.department.DTO.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.DTO.UpdatedDepartmentDTO;
import com.travel.safe.buses.domain.department.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "departmentManager", ignore = true)
  Department toEntity(CreateDepartmentDto department);

  @Mapping(target = "departmentManager", ignore = true)
  Department toEntity(UpdatedDepartmentDTO department);

  @Mapping(target = "salary", ignore = true)
  @Mapping(target = "departmentManager", ignore = true)
  Department responseDtoToEntity(DepartmentResponseDTO responseDTO);

  DepartmentResponseDTO entityToDto(Department department);
}