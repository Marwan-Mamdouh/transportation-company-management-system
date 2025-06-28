package com.travel.safe.buses.domain.vehicle.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.vehicle.VehicleMapper;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.dto.UpdateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.dto.VehicleResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateVehicleService implements Command<UpdateVehicleDTO, VehicleResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateVehicleService.class);
  private final VehicleRepository vehicleRepository;
  private final GetVehicleService getVehicleService;
  private final VehicleMapper mapper;

  public UpdateVehicleService(VehicleRepository vehicleRepository,
      GetVehicleService getVehicleService, VehicleMapper vehicleMapper) {
    this.vehicleRepository = vehicleRepository;
    this.getVehicleService = getVehicleService;
    this.mapper = vehicleMapper;
  }

  @Override
  public VehicleResponseDTO execute(UpdateVehicleDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    getVehicleService.execute(input.vehicleId());
    final var updatedVehicle = vehicleRepository.save(mapper.toEntity(input));
    return mapper.toResponseDto(updatedVehicle);
  }
}