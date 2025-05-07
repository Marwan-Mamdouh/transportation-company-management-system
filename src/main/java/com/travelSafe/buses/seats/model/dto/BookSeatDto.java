package com.travelSafe.buses.seats.model.dto;

import com.travelSafe.buses.validator.PatternLong;
import jakarta.validation.constraints.Positive;

public record BookSeatDto(@Positive Integer tripId, @Positive Integer seatNo,
                          @Positive @PatternLong(regexp = "^[2-3][0-9]{13}$") Long ssn) {

}
