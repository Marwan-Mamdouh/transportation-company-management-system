package com.travelSafe.buses.trip.model.DTO;

import com.travelSafe.buses.trip.model.Trip;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TripAvailabilityDTO {

  private final TripDto trip;
  private final Long availableSeatCount;

  public TripAvailabilityDTO(Trip trip, Long count) {
    this.trip = new TripDto(trip);
    this.availableSeatCount = count;
  }
}