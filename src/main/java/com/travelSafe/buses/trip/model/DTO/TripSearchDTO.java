package com.travelSafe.buses.trip.model.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record SearchFreeSeatsDTO(@NotNull @FutureOrPresent LocalDate travelDate,
                                 @NotBlank String startFrom, @NotBlank String destination) {

}