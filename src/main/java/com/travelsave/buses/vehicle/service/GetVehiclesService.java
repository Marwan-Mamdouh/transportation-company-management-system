package com.travelsave.buses.vehicle.service;

import com.travelsave.buses.Query;
import com.travelsave.buses.vehicle.VehicleRepository;
import com.travelsave.buses.vehicle.model.Vehicle;
import com.travelsave.buses.vehicle.model.VehicleDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetVehiclesService implements Query<Void, List<VehicleDTO>> {

  private final VehicleRepository vehicleRepository;

  public GetVehiclesService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public ResponseEntity<List<VehicleDTO>> execute(Void input) {
    List<Vehicle> vehicles = vehicleRepository.findAll();
    List<VehicleDTO> vehicleDTOS = vehicles.stream().map(VehicleDTO::new).toList();
    return ResponseEntity.ok(vehicleDTOS);
  }
}