package com.travelSafe.buses.department.model.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateDepartmentDto(
    @NotBlank(message = "Department name Required.") @Size(max = 55, message = "Department name length should be 55 character max.") String name,
    @Positive @Max(10000000) @NotNull(message = "Salary Required for creating department.") Integer salary,
    @Max(16) @Positive @NotNull(message = "Working hours Required for creating department.") Double workingHoursPerDay) {

}
