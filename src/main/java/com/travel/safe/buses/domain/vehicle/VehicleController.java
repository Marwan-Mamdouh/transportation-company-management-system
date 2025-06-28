package com.travel.safe.buses.domain.vehicle;

import com.travel.safe.buses.domain.vehicle.dto.CreateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.dto.UpdateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.dto.VehicleResponseDTO;
import com.travel.safe.buses.domain.vehicle.service.CreateVehicleService;
import com.travel.safe.buses.domain.vehicle.service.DeleteVehicleService;
import com.travel.safe.buses.domain.vehicle.service.GetVehicleService;
import com.travel.safe.buses.domain.vehicle.service.GetVehiclesService;
import com.travel.safe.buses.domain.vehicle.service.UpdateVehicleService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
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

  @PostMapping
  public ResponseEntity<VehicleResponseDTO> createVehicle(
      @Valid @RequestBody CreateVehicleDTO vehicle) {
    return ResponseEntity.ok(createVehicleService.execute(vehicle));
  }

  @GetMapping("/all")
  public ResponseEntity<List<VehicleResponseDTO>> getVehicles() {
    return ResponseEntity.ok(getVehiclesService.execute(null));
  }

  @GetMapping("/{vehicleId}")
  public ResponseEntity<VehicleResponseDTO> getVehicle(@PathVariable Integer vehicleId) {
    return ResponseEntity.ok(getVehicleService.execute(vehicleId));
  }

  @PutMapping
  public ResponseEntity<VehicleResponseDTO> updateVehicle(
      @Valid @RequestBody UpdateVehicleDTO vehicleDto) {
    return ResponseEntity.ok(updateVehicleService.execute(vehicleDto));
  }

  @DeleteMapping("/{vehicleId}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable Integer vehicleId) {
    return ResponseEntity.ok(deleteVehicleService.execute(vehicleId));
  }
}