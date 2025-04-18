package com.travelsave.buses.vehicle.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.vehicle.VehicleRepository;
import com.travelsave.buses.vehicle.model.UpdateVehicleCommand;
import com.travelsave.buses.vehicle.model.Vehicle;
import com.travelsave.buses.vehicle.model.VehicleDTO;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateVehicleService implements Command<UpdateVehicleCommand, VehicleDTO> {

  private final VehicleRepository vehicleRepository;

  public UpdateVehicleService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public ResponseEntity<VehicleDTO> execute(UpdateVehicleCommand input) {
    Optional<Vehicle> optionalVehicle = vehicleRepository.findById(input.vehicleId());
    if (optionalVehicle.isPresent()) {
      vehicleRepository.save(input.vehicle());
      return ResponseEntity.ok(new VehicleDTO(optionalVehicle.get()));
    }
    throw new RuntimeException();
  }
}