package com.travelSafe.buses.employee.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeLoginDTO(@NotBlank @Email String email, @NotBlank String password) {

}