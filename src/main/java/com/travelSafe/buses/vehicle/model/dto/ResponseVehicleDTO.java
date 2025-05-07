package com.travelSafe.buses.vehicle.model.dto;

import com.travelSafe.buses.vehicle.model.Vehicle;
import lombok.Data;

@Data
public class ResponseVehicleDTO {

  private final Integer vehicleId, capacity;
  private final String vehicleLevel, plateNumber;

  public ResponseVehicleDTO(Vehicle vehicle) {
    this.vehicleId = vehicle.getVehicleId();
    this.capacity = vehicle.getCapacity();
    this.vehicleLevel = vehicle.getVehicleLevel();
    this.plateNumber = vehicle.getPlateNumber();
  }
}