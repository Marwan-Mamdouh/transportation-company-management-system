package com.travelSafe.buses.domain.employee;

import com.travelSafe.buses.domain.employee.dto.InputEmployeeDTO;
import com.travelSafe.buses.domain.employee.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
//  Employee ToDTO(CreateEmployeeDTO employee);


  Employee employeeFromInputDTO(InputEmployeeDTO dto);
}