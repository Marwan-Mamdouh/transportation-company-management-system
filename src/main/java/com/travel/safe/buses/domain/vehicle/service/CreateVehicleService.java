package com.travel.safe.buses.domain.vehicle.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.vehicle.VehicleMapper;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.dto.CreateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.dto.VehicleResponseDTO;
import com.travel.safe.buses.domain.vehicle.exceptions.DuplicateVehiclePlateNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleService implements Command<CreateVehicleDTO, VehicleResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(CreateVehicleService.class);
  private final VehicleRepository vehicleRepository;
  private final VehicleMapper mapper;

  public CreateVehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
    this.vehicleRepository = vehicleRepository;
    this.mapper = vehicleMapper;
  }

  @Override
  public VehicleResponseDTO execute(CreateVehicleDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    if (vehicleRepository.existsByPlateNumberIgnoreCase(input.plateNumber())) {
      throw new DuplicateVehiclePlateNumberException();
    }
    final var savedVehicle = vehicleRepository.save(mapper.toEntity(input));
    return mapper.toResponseDto(savedVehicle);
  }
}