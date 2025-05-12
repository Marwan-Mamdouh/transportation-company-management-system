package com.travelSafe.buses.employee.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeLoginDTO(@NotBlank @Email @Size(max = 99) String email,
                               @NotBlank String password) {

}