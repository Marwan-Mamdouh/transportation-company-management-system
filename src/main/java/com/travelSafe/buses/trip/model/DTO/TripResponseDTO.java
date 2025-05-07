package com.travelSafe.buses.trip.model.DTO;

import com.travelSafe.buses.trip.model.Trip;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TripResponseDTO {

  private final Integer tripId, price;
  private final LocalDateTime travelDateAndTime;

  public TripResponseDTO(Trip trip) {
    this.tripId = trip.getTripId();
    this.price = trip.getPrice();
    this.travelDateAndTime = trip.getTravelDateAndTime();
  }
}