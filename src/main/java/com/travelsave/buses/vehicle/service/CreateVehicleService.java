package com.travelsave.buses.vehicle.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.vehicle.VehicleRepository;
import com.travelsave.buses.vehicle.model.Vehicle;
import com.travelsave.buses.vehicle.model.VehicleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleService implements Command<Vehicle, VehicleDTO> {

  private final VehicleRepository vehicleRepository;

  public CreateVehicleService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public ResponseEntity<VehicleDTO> execute(Vehicle input) {
    Vehicle savedVehicle = vehicleRepository.save(input);
    return ResponseEntity.ok(new VehicleDTO(savedVehicle));
  }
}