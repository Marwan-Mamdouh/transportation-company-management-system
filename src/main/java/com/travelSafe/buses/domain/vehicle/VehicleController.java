package com.travelSafe.buses.domain.vehicle;

import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import com.travelSafe.buses.domain.vehicle.model.dto.CreateVehicleDTO;
import com.travelSafe.buses.domain.vehicle.model.dto.UpdateVehicleDTO;
import com.travelSafe.buses.domain.vehicle.model.dto.VehicleResponseDTO;
import com.travelSafe.buses.domain.vehicle.service.CreateVehicleService;
import com.travelSafe.buses.domain.vehicle.service.DeleteVehicleService;
import com.travelSafe.buses.domain.vehicle.service.GetVehicleService;
import com.travelSafe.buses.domain.vehicle.service.GetVehiclesService;
import com.travelSafe.buses.domain.vehicle.service.UpdateVehicleService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

  private final CreateVehicleService createVehicleService;

  private final UpdateVehicleService updateVehicleService;

  private final GetVehicleService getVehicleService;
  private final GetVehiclesService getVehiclesService;

  private final DeleteVehicleService deleteVehicleService;

  public VehicleController(CreateVehicleService createVehicleService,
      UpdateVehicleService updateVehicleService, GetVehicleService getVehicleService,
      GetVehiclesService getVehiclesService, DeleteVehicleService deleteVehicleService) {
    this.createVehicleService = createVehicleService;
    this.updateVehicleService = updateVehicleService;
    this.getVehicleService = getVehicleService;
    this.getVehiclesService = getVehiclesService;
    this.deleteVehicleService = deleteVehicleService;
  }

  @PostMapping("/vehicle")
  public ResponseEntity<VehicleResponseDTO> createVehicle(
      @Valid @RequestBody CreateVehicleDTO vehicle) {
    final Vehicle savedVehicle = createVehicleService.execute(vehicle);
    return ResponseEntity.ok(new VehicleResponseDTO(savedVehicle));
  }

  @GetMapping("/vehicles")
  public ResponseEntity<List<VehicleResponseDTO>> getVehicles() {
    final List<Vehicle> vehicles = getVehiclesService.execute(null);
    return ResponseEntity.ok(vehicles.stream().map(VehicleResponseDTO::new).toList());
  }

  @GetMapping("/vehicle/{vehicleId}")
  public ResponseEntity<VehicleResponseDTO> getVehicle(@PathVariable Integer vehicleId) {
    final Vehicle vehicle = getVehicleService.execute(vehicleId);
    return ResponseEntity.ok(new VehicleResponseDTO(vehicle));
  }

  @PutMapping("/vehicle")
  public ResponseEntity<VehicleResponseDTO> updateVehicle(
      @Valid @RequestBody UpdateVehicleDTO vehicleDto) {
    final Vehicle updatedVehicle = updateVehicleService.execute(vehicleDto);
    return ResponseEntity.ok(new VehicleResponseDTO(updatedVehicle));
  }

  @DeleteMapping("/vehicle/{vehicleId}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable Integer vehicleId) {
    return ResponseEntity.ok(deleteVehicleService.execute(vehicleId));
  }
}