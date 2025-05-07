package com.travelSafe.buses.vehicle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
  @Max(255)
  @Column(name = "car_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer vehicleId;

  @NotBlank
  @Column(nullable = false)
  @Size(max = 10, message = "plate number must be 10 character max.")
  private String plateNumber;

  @Max(60)
  @Positive
  private Integer capacity;

  @Column(nullable = false)
  @Size(max = 10, message = "vehicle level must be 10 character max.")
  private String vehicleLevel;

  private LocalDate licenseIssueDate;

  @NotNull
  @Column(nullable = false)
  private LocalDate licenseExpiryDate;
}