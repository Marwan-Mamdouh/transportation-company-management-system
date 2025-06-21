package com.travel.safe.buses.domain.seats.model;

import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.trip.model.Trip;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "trip_seats")
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

  @EmbeddedId
  private SeatId tripSeatId;

  @MapsId("tripId")
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
  @JoinColumn(name = "trip_id", nullable = false)
  private Trip tripId;

  @JoinColumn(name = "booked_by")
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
  private Employee bookedBy;

  private LocalDateTime orderTime;
}