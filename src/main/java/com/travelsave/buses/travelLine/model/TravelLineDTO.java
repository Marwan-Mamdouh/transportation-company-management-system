package com.travelsave.buses.travelLine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TravelLineDTO {

  private final String from;
  private final String destination;
  private final String[] path;

  public TravelLineDTO(TravelLine travelLine) {
    this.from = travelLine.getStartFrom();
    this.destination = travelLine.getDestination();
    this.path = travelLine.getPath();
  }
}
