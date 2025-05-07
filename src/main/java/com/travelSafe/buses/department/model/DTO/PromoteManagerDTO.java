package com.travelSafe.buses.department.model.DTO;

import com.travelSafe.buses.department.model.Department;
import com.travelSafe.buses.validator.PatternLong;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record PromoteManagerDTO(
    @Max(39999999999999L) @PatternLong(regexp = "^[2-3][0-9]{13}$") Long managerId,
    @NotNull Department department) {

}