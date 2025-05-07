package com.travelSafe.buses.vehicle;

import com.travelSafe.buses.vehicle.model.Vehicle;
import com.travelSafe.buses.vehicle.model.dto.CreateVehicleDto;
import com.travelSafe.buses.vehicle.model.dto.ResponseVehicleDTO;
import com.travelSafe.buses.vehicle.model.dto.UpdateVehicleDto;
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
  public ResponseEntity<ResponseVehicleDTO> createVehicle(@Valid @RequestBody CreateVehicleDto vehicle) {
    final Vehicle savedVehicle = createVehicleService.execute(vehicle);
    return ResponseEntity.ok(new ResponseVehicleDTO(savedVehicle));
  }

  @GetMapping("/vehicles")
  public ResponseEntity<List<ResponseVehicleDTO>> getVehicles() {
    final List<Vehicle> vehicles = getVehiclesService.execute(null);
    return ResponseEntity.ok(vehicles.stream().map(ResponseVehicleDTO::new).toList());
  }

  @GetMapping("/vehicle/{vehicleId}")
  public ResponseEntity<ResponseVehicleDTO> getVehicle(@PathVariable Integer vehicleId) {
    final Vehicle vehicle = getVehicleService.execute(vehicleId);
    return ResponseEntity.ok(new ResponseVehicleDTO(vehicle));
  }

  @PutMapping("/vehicle")
  public ResponseEntity<ResponseVehicleDTO> updateVehicle(
      @Valid @RequestBody UpdateVehicleDto vehicleDto) {
    final Vehicle updatedVehicle = updateVehicleService.execute(vehicleDto);
    return ResponseEntity.ok(new ResponseVehicleDTO(updatedVehicle));
  }

  @DeleteMapping("/vehicle/{vehicleId}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable Integer vehicleId) {
    return ResponseEntity.ok(deleteVehicleService.execute(vehicleId));
  }
}