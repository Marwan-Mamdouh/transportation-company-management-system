package com.travel.safe.buses.domain.department.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdatedDepartmentDTO(
    @NotNull(message = "department ID must be not null.") @Positive @Max(255) Integer id,
    @NotBlank(message = "Department name Required.") @Size(max = 55, message = "Department name length should be 55 character max.") String name,
    @NotNull(message = "Salary Required for creating department.") @Positive @Max(10000000) Integer salary,
    @NotNull(message = "Working hours Required for creating department.") @Positive @Max(16) Double workingHoursPerDay) {
// @PatternLong(regexp = "^[2-3][0-9]{13}$") Long departmentManger
}