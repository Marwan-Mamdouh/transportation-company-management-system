package com.travelSafe.buses.vehicle.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.vehicle.VehicleRepository;
import com.travelSafe.buses.vehicle.model.Vehicle;
import com.travelSafe.buses.vehicle.model.VehicleMapper;
import com.travelSafe.buses.vehicle.model.dto.UpdateVehicleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateVehicleService implements Command<UpdateVehicleDto, Vehicle> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateVehicleService.class);
  private final VehicleRepository vehicleRepository;
  private final GetVehicleService getVehicleService;
  private final VehicleMapper vehicleMapper;

  public UpdateVehicleService(VehicleRepository vehicleRepository,
      GetVehicleService getVehicleService, VehicleMapper vehicleMapper) {
    this.vehicleRepository = vehicleRepository;
    this.getVehicleService = getVehicleService;
    this.vehicleMapper = vehicleMapper;
  }

  @Override
  public Vehicle execute(UpdateVehicleDto input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    getVehicleService.execute(input.vehicleId());
    return vehicleRepository.save(vehicleMapper.fromUpdateDtoToEntity(input));
  }
}