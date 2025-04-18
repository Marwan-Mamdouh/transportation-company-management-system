package com.travelsave.buses.travelLine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TravelLine {

  @Id
  @Column(name = "line_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer lineId;

  @Column(name = "start_from")
  private String startFrom;

  private String destination;

  @Column(unique = true)
  private String[] path;
}
