package com.travelsave.buses.vehicle.service;

import com.travelsave.buses.Query;
import com.travelsave.buses.vehicle.VehicleRepository;
import com.travelsave.buses.vehicle.model.Vehicle;
import com.travelsave.buses.vehicle.model.VehicleDTO;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetVehicleService implements Query<Integer, VehicleDTO> {

  private final VehicleRepository vehicleRepository;

  public GetVehicleService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public ResponseEntity<VehicleDTO> execute(Integer input) {
    Optional<Vehicle> optionalVehicle = vehicleRepository.findById(input);
    if (optionalVehicle.isPresent()) {
      return ResponseEntity.ok(new VehicleDTO(optionalVehicle.get()));
    }
    throw new RuntimeException();
  }
}