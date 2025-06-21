package com.travel.safe.buses.domain.travelLine.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TravelLine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer lineId;

  private String startFrom;

  private String destination;

  @ElementCollection
  @CollectionTable(name = "travel_stations", joinColumns = @JoinColumn(name = "line_id"))
  private List<String> stations;
}