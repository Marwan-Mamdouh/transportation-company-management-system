package com.travelSafe.buses.vehicle.model.dto;

import com.travelSafe.buses.vehicle.model.Vehicle;
import lombok.Data;

@Data
public class VehicleDTO {

  private final Integer vehicleId;
  private final Integer capacity;
  private final String vehicleLevel;
  private final String plateNumber;

  public VehicleDTO(Vehicle vehicle) {
    this.vehicleId = vehicle.getVehicleId();
    this.capacity = vehicle.getCapacity();
    this.vehicleLevel = vehicle.getVehicleLevel();
    this.plateNumber = vehicle.getPlateNumber();
  }
}