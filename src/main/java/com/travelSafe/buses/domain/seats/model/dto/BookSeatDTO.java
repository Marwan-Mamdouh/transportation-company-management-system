package com.travelSafe.buses.domain.seats.model.dto;

import com.travelSafe.buses.domain.seats.model.SeatId;
import com.travelSafe.buses.validator.PatternLong;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookSeatDTO(@NotNull @Positive Integer tripId,
                          @NotNull @Positive @Max(60) Integer seatNo,
                          @NotNull @Positive @PatternLong(regexp = "^[2-3][0-9]{13}$") Long ssn) {

  public SeatId toSeatId() {
    return new SeatId(tripId, seatNo);
  }
}