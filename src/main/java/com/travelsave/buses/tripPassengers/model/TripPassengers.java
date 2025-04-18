package com.travelsave.buses.tripPassengers.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trip_passengers")
public class TripPassengers {

  @Id
  @EmbeddedId
  private TripPassengerPk tripPassengerPk;
}