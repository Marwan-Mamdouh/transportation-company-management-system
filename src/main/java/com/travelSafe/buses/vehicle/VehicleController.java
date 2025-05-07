package com.travelSafe.buses.vehicle;

import com.travelSafe.buses.vehicle.model.UpdateVehicleCommand;
import com.travelSafe.buses.vehicle.model.Vehicle;
import com.travelSafe.buses.vehicle.model.VehicleDTO;
import com.travelSafe.buses.vehicle.service.CreateVehicleService;
import com.travelSafe.buses.vehicle.service.DeleteVehicleService;
import com.travelSafe.buses.vehicle.service.GetVehicleService;
import com.travelSafe.buses.vehicle.service.GetVehiclesService;
import com.travelSafe.buses.vehicle.service.UpdateVehicleService;
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

@RestController("/vehicle")
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
  public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody Vehicle vehicle) {
    final Vehicle savedVehicle = createVehicleService.execute(vehicle);
    return ResponseEntity.ok(new VehicleDTO(savedVehicle));
  }

  @GetMapping("/vehicles")
  public ResponseEntity<List<VehicleDTO>> getVehicles() {
    final List<Vehicle> vehicles = getVehiclesService.execute(null);
    return ResponseEntity.ok(vehicles.stream().map(VehicleDTO::new).toList());
  }

  @GetMapping("/vehicle/{vehicleId}")
  public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Integer vehicleId) {
    final Vehicle vehicle = getVehicleService.execute(vehicleId);
    return ResponseEntity.ok(new VehicleDTO(vehicle));
  }

  @PutMapping("/vehicle/{vehicleId}")
  public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Integer vehicleId,
      @RequestBody Vehicle vehicle) {
    final Vehicle updatedVehicle = updateVehicleService.execute(
        new UpdateVehicleCommand(vehicleId, vehicle));
    return ResponseEntity.ok(new VehicleDTO(updatedVehicle));
  }

  @DeleteMapping("/vehicle/{vehicleId}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable Integer vehicleId) {
    return ResponseEntity.ok(deleteVehicleService.execute(vehicleId));
  }
}