package com.travelsave.buses.vehicle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
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

  @Positive
  private Integer capacity;

  @Column(name = "vehicle_level", nullable = false)
  private String vehicleLevel;

  @Column(name = "license_issue_date")
  private LocalDate licenseIssueDate;

  @Column(name = "license_expiry_date", nullable = false)
  private LocalDate licenseExpiryDate;

  @Column(name = "plate_number", nullable = false)
  private String plateNumber;
}