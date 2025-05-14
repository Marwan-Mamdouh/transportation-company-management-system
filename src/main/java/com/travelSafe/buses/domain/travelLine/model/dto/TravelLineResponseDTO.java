package com.travelSafe.buses.domin.travelLine.model.dto;

import com.travelSafe.buses.domin.travelLine.model.TravelLine;
import java.util.List;
import lombok.Data;

@Data
public class TravelLineResponseDTO {

  private final String startFrom;
  private final String destination;
  private final List<String> stations;

  public TravelLineResponseDTO(TravelLine travelLine) {
    this.startFrom = travelLine.getStartFrom();
    this.destination = travelLine.getDestination();
    this.stations = travelLine.getStations();
  }
}