package com.travelSafe.buses.domain.trip.DTO;

import com.travelSafe.buses.comman.validator.PatternLong;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record UpdateTripDTO(@NotNull @Positive Integer tripId,
                            @NotNull @Positive @Max(255) Integer car,
                            @NotNull @Positive @Max(255) Integer travelLine,
                            @NotNull @Positive @PatternLong(regexp = "^[2-3][0-9]{13}$") Long driver,
                            @NotNull @Positive @Max(10000) Integer price,
                            @NotNull LocalDateTime travelDateAndTime) {

}