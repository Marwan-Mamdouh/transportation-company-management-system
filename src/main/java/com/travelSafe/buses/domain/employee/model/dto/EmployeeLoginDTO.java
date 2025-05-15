package com.travelSafe.buses.domain.employee.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeLoginDTO(@NotBlank @Email @Size(max = 99) String email,
                               @NotBlank(message = "Password can't be null or empty.") @Size(min = 8, max = 20, message = "Password length should be between 8 and 20 character.") String password) {

}