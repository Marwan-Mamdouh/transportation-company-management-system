package com.travelSafe.buses.domin.employee.model;

import com.travelSafe.buses.domin.employee.model.DTO.InputEmployeeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
//  Employee ToDTO(CreateEmployeeDTO employee);


  Employee employeeFromInputDTO(InputEmployeeDTO dto);
}