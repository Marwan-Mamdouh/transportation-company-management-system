package com.travelSafe.buses.employee.model.DTO;

import com.travelSafe.buses.employee.model.Employee;

public record UpdateEmployeeDTO(Long id, Employee updateedEmployee) {

}