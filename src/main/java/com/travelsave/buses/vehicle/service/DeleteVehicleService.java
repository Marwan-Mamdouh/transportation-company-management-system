package com.travelsave.buses.vehicle.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.vehicle.VehicleRepository;
import com.travelsave.buses.vehicle.model.Vehicle;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteVehicleService implements Command<Integer, Void> {

  private final VehicleRepository vehicleRepository;

  public DeleteVehicleService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Integer input) {
    Optional<Vehicle> optionalVehicle = vehicleRepository.findById(input);
    if (optionalVehicle.isPresent()) {
      vehicleRepository.deleteById(input);
      ResponseEntity.noContent().build();
    }
    throw new RuntimeException();
  }
}