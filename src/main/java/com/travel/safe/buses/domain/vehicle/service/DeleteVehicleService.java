package com.travel.safe.buses.domain.vehicle.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteVehicleService implements Command<Integer, Void> {

  private static final Logger logger = LoggerFactory.getLogger(DeleteVehicleService.class);
  private final VehicleRepository vehicleRepository;
  private final GetVehicleService getVehicleService;

  public DeleteVehicleService(VehicleRepository vehicleRepository,
      GetVehicleService getVehicleService) {
    this.vehicleRepository = vehicleRepository;
    this.getVehicleService = getVehicleService;
  }

  @Override
  public Void execute(Integer input) {
    logger.debug("Executing: {} with input:{}", getClass(), input);
    getVehicleService.execute(input);
    vehicleRepository.deleteById(input);
    return null;
  }
}