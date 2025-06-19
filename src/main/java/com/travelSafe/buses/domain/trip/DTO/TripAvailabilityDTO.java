package com.travelSafe.buses.domain.trip.DTO;

import com.travelSafe.buses.domain.trip.model.Trip;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TripAvailabilityDTO {

  private final TripResponseDTO trip;
  private final Long availableSeatCount;

  public TripAvailabilityDTO(Trip trip, Long count) {
    this.trip = new TripResponseDTO(trip);
    this.availableSeatCount = count;
  }
}