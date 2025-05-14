package com.travelSafe.buses.domain.vehicle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Vehicle {

  @Id
  @Column(name = "car_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer vehicleId;

  @Column(nullable = false, unique = true)
  private String plateNumber;

  private Integer capacity;

  @Column(nullable = false)
  private String vehicleLevel;

  private LocalDate licenseIssueDate;

  @Column(nullable = false)
  private LocalDate licenseExpiryDate;
}