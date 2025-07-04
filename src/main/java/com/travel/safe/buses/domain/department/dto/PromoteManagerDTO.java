package com.travel.safe.buses.domain.department.dto;

import com.travel.safe.buses.comman.validator.PatternLong;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PromoteManagerDTO(
    @NotNull @Positive @PatternLong(regexp = "^[2-3][0-9]{13}$") Long managerId,
    @NotNull @Positive Integer departmentId) {

}