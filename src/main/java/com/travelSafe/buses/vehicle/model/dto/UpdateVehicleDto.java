package com.travelSafe.buses.vehicle.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UpdateVehicleDto(@Max(255) Integer vehicleId,
                               @NotBlank(message = "Plate number can not be null or empty") @Size(max = 10, message = "plate number must be 10 character max.") String plateNumber,
                               @Positive @Max(value = 60, message = "What is this vehicle that can hold more then 60 person ?") Integer capacity,
                               @NotBlank(message = "vehicle level can not be null or blank.") @Size(max = 10) String vehicleLevel,
                               @NotBlank LocalDate licenseIssueDate,
                               @NotBlank LocalDate licenseExpiryDate) {

}