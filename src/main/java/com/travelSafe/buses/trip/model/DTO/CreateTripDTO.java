package com.travelSafe.buses.trip.model.DTO;

import com.travelSafe.buses.validator.PatternLong;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record CreateTripDTO(@Positive @Max(255) Integer car, @Positive @Max(255) Integer travelLine,
                            @Positive @NotNull @Max(39999999999999L) @PatternLong(regexp = "^[2-3][0-9]{13}$") Long driver,
                            @NotNull @Positive @Max(10000) Integer price,
                            @NotNull LocalDateTime travelDateAndTime) {

}