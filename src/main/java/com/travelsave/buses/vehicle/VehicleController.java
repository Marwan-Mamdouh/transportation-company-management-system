package com.travelsave.buses.vehicle;

import com.travelsave.buses.vehicle.model.UpdateVehicleCommand;
import com.travelsave.buses.vehicle.model.Vehicle;
import com.travelsave.buses.vehicle.model.VehicleDTO;
import com.travelsave.buses.vehicle.service.CreateVehicleService;
import com.travelsave.buses.vehicle.service.DeleteVehicleService;
import com.travelsave.buses.vehicle.service.GetVehicleService;
import com.travelsave.buses.vehicle.service.GetVehiclesService;
import com.travelsave.buses.vehicle.service.UpdateVehicleService;
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
  public ResponseEntity<VehicleDTO> createVehicle(@RequestBody Vehicle vehicle) {
    return createVehicleService.execute(vehicle);
  }

  @GetMapping("/vehicles")
  public ResponseEntity<List<VehicleDTO>> getVehicles() {
    return getVehiclesService.execute(null);
  }

  @GetMapping("/vehicle/{vehicleId}")
  public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Integer vehicleId) {
    return getVehicleService.execute(vehicleId);
  }

  @PutMapping("/vehicle/{vehicleId}")
  public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Integer vehicleId,
      @RequestBody Vehicle vehicle) {
    return updateVehicleService.execute(new UpdateVehicleCommand(vehicleId, vehicle));
  }

  @DeleteMapping("/vehicle/{vehicleId}")
  public ResponseEntity<Void> deleteVehicle(@PathVariable Integer vehicleId) {
    return deleteVehicleService.execute(vehicleId);
  }
}