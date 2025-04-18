package com.travelsave.buses.trip.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripDTO {

  private final String tripLevel;
  private final Double price;
  private final LocalDate tripDate;

  public TripDTO(Trip trip) {
    this.tripLevel = trip.getTripLevel();
    this.price = trip.getPrice();
    this.tripDate = trip.getTravelDate();
  }
}