package com.travel.safe.buses.domain.trip.model;

import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.seats.model.Seat;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tripId;

  @JoinColumn(name = "car_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Vehicle car;

  @JoinColumn(name = "line_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private TravelLine travelLine;

  @JoinColumn(name = "driver_ssn", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Employee driver;

  @Column(name = "trip_price", nullable = false)
  private Integer price;

  @NotNull
  private LocalDateTime travelDateAndTime;

  @OneToMany(mappedBy = "tripId", cascade = {CascadeType.PERSIST,
      CascadeType.MERGE}, fetch = FetchType.LAZY)
  private List<Seat> seats;
}