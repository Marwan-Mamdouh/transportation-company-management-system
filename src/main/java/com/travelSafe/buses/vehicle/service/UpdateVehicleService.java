package com.travelSafe.buses.vehicle.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.vehicle.VehicleRepository;
import com.travelSafe.buses.vehicle.model.UpdateVehicleCommand;
import com.travelSafe.buses.vehicle.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateVehicleService implements Command<UpdateVehicleCommand, Vehicle> {

  private final VehicleRepository vehicleRepository;
  private final GetVehicleService getVehicleService;

  private static final Logger logger = LoggerFactory.getLogger(UpdateVehicleService.class);

  public UpdateVehicleService(VehicleRepository vehicleRepository,
      GetVehicleService getVehicleService) {
    this.vehicleRepository = vehicleRepository;
    this.getVehicleService = getVehicleService;
  }

  @Override
  public Vehicle execute(UpdateVehicleCommand input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    final Integer vehicleId = input.vehicleId();
    getVehicleService.execute(vehicleId);
    final Vehicle updatedVehicle = input.vehicle();
    updatedVehicle.setVehicleId(vehicleId);
    return vehicleRepository.save(updatedVehicle);
  }
}