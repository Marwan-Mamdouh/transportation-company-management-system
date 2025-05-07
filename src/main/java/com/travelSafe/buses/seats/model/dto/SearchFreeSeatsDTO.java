package com.travelSafe.buses.seats.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record SearchFreeSeatsDTO(@FutureOrPresent @NotNull LocalDate travelDate,
                                 @NotBlank String startFrom, @NotBlank String destination) {

}
