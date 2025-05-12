package com.travelSafe.buses.employee.model;

import com.travelSafe.buses.employee.model.DTO.InputEmployeeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
//  Employee ToDTO(CreateEmployeeDTO employee);


  Employee employeeFromInputDTO(InputEmployeeDTO dto);
}