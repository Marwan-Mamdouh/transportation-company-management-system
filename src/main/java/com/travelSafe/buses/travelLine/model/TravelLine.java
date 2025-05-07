package com.travelSafe.buses.travelLine.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
  @Max(255)
  @Positive
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer lineId;

  @Size(max = 55, message = "station name must be 55 character max.")
  private String startFrom;

  @Size(max = 55, message = "station name must be 55 character max.")
  private String destination;

  @ElementCollection
  @Column(name = "station")
  @Size(max = 55, message = "station name must be 55 character max.")
  @CollectionTable(name = "travel_stations", joinColumns = @JoinColumn(name = "line_id"))
  private List<String> stations;
}