package com.travelSafe.buses.domain.vehicle.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domain.vehicle.VehicleRepository;
import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import com.travelSafe.buses.domain.vehicle.model.VehicleMapper;
import com.travelSafe.buses.domain.vehicle.model.dto.CreateVehicleDTO;
import com.travelSafe.buses.exceptions.vehicle.DuplicateVehiclePlateNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleService implements Command<CreateVehicleDTO, Vehicle> {

  private static final Logger logger = LoggerFactory.getLogger(CreateVehicleService.class);
  private final VehicleRepository vehicleRepository;
  private final VehicleMapper vehicleMapper;

  public CreateVehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
    this.vehicleRepository = vehicleRepository;
    this.vehicleMapper = vehicleMapper;
  }

  @Override
  public Vehicle execute(CreateVehicleDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    if (vehicleRepository.existsByPlateNumberIgnoreCase(input.plateNumber())) {
      throw new DuplicateVehiclePlateNumberException();
    }
    return vehicleRepository.save(vehicleMapper.fromCreateDtoToEntity(input));
  }
}