package com.travel.safe.buses.domain.employee;

import com.travel.safe.buses.domain.employee.dto.CreateEmployeeDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeePaycheckDTO;
import com.travel.safe.buses.domain.employee.dto.EmployeeResponseDTO;
import com.travel.safe.buses.domain.employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeResponseDTO responseDTOFromEmployee(Employee employee);

  @Mapping(target = "salary", source = "department.salary")
  EmployeePaycheckDTO payCheckDTOFromEmployee(Employee employee);

  @Mapping(target = "age", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "department", ignore = true)
  @Mapping(target = "supervisor", ignore = true)
  Employee employeeCreateEmployeeDTO(CreateEmployeeDTO dto);
}