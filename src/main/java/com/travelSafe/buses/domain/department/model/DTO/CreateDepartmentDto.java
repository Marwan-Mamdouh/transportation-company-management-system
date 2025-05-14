package com.travelSafe.buses.domain.department.model.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateDepartmentDto(
    @NotBlank(message = "Department name can't be null or blank.") @Size(max = 55, message = "Department name length should be 55 character max.") String name,
    @NotNull @Positive @Max(10000000) @NotNull(message = "Salary must be not null and positive.") Integer salary,
    @NotNull @Positive @Max(16) @NotNull(message = "Working hours Required for creating department.") Double workingHoursPerDay) {

}