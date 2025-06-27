package com.travel.safe.buses.domain.department;

import com.travel.safe.buses.domain.department.dto.CreateDepartmentDto;
import com.travel.safe.buses.domain.department.dto.DepartmentResponseDTO;
import com.travel.safe.buses.domain.department.dto.UpdatedDepartmentDTO;
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