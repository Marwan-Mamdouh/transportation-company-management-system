package com.travelSafe.buses.seats.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SeatId implements Serializable {

  @Max(60)
  @Positive
  private Integer seatNumber;

  @NotNull
  @Positive
  private Integer tripId;
}