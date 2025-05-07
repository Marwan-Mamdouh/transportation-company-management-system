package com.travelSafe.buses.trip.model.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record UpdateTripDTO(@Positive Integer tripId, @Max(255) Integer car,
                            @Max(255) @Positive Integer travelLine, @Positive Long driver,
                            @NotNull @Positive @Max(10000) Integer price,
                            @NotNull LocalDateTime travelDateAndTime) {

}