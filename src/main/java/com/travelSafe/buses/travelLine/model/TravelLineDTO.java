package com.travelSafe.buses.travelLine.model;

import java.util.List;
import lombok.Data;

@Data
public class TravelLineDTO {

  private final String startFrom;
  private final String destination;
  private final List<String> stations;

  public TravelLineDTO(TravelLine travelLine) {
    this.startFrom = travelLine.getStartFrom();
    this.destination = travelLine.getDestination();
    this.stations = travelLine.getStations();
  }
}