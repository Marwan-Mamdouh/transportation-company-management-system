package com.travelSafe.buses.vehicle.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.vehicle.VehicleRepository;
import com.travelSafe.buses.vehicle.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleService implements Command<Vehicle, Vehicle> {

  private final VehicleRepository vehicleRepository;

  private static final Logger logger = LoggerFactory.getLogger(CreateVehicleService.class);

  public CreateVehicleService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public Vehicle execute(Vehicle input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return vehicleRepository.save(input);
  }
}