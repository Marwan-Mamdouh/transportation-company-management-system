package com.travelSafe.buses.domain.vehicle.model.dto;

import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import lombok.Data;

@Data
public class VehicleResponseDTO {

  private final Integer vehicleId, capacity;
  private final String vehicleLevel, plateNumber;

  public VehicleResponseDTO(Vehicle vehicle) {
    this.vehicleId = vehicle.getVehicleId();
    this.capacity = vehicle.getCapacity();
    this.vehicleLevel = vehicle.getVehicleLevel();
    this.plateNumber = vehicle.getPlateNumber();
  }
}