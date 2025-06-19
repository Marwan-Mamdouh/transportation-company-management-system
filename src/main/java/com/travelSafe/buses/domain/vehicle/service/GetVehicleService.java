package com.travelSafe.buses.domain.vehicle.service;

import com.travelSafe.buses.comman.shared.Query;
import com.travelSafe.buses.domain.vehicle.VehicleRepository;
import com.travelSafe.buses.domain.vehicle.exceptions.VehicleNotFoundException;
import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetVehicleService implements Query<Integer, Vehicle> {

  private static final Logger logger = LoggerFactory.getLogger(GetVehicleService.class);
  private final VehicleRepository vehicleRepository;

  public GetVehicleService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public Vehicle execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return vehicleRepository.findById(input).orElseThrow(VehicleNotFoundException::new);
  }
}