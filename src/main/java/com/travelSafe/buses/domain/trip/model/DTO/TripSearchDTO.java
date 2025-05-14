package com.travelSafe.buses.domain.trip.model.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TripSearchDTO(@NotNull @FutureOrPresent LocalDate travelDate,
                            @NotBlank @Size(max = 55) String startFrom,
                            @NotBlank @Size(max = 55) String destination) {

}