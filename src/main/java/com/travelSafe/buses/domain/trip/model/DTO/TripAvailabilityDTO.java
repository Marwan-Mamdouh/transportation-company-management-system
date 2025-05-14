package com.travelSafe.buses.domin.trip.model.DTO;

import com.travelSafe.buses.domin.trip.model.Trip;
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