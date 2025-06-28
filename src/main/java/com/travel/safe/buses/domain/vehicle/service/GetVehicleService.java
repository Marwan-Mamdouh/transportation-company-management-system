package com.travel.safe.buses.domain.vehicle.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.vehicle.VehicleMapper;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.dto.VehicleResponseDTO;
import com.travel.safe.buses.domain.vehicle.exceptions.VehicleNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetVehicleService implements Query<Integer, VehicleResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(GetVehicleService.class);
  private final VehicleRepository vehicleRepository;
  private final VehicleMapper mapper;

  public GetVehicleService(VehicleRepository vehicleRepository, VehicleMapper mapper) {
    this.vehicleRepository = vehicleRepository;
    this.mapper = mapper;
  }

  @Override
  public VehicleResponseDTO execute(Integer input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var foundVehicle = vehicleRepository.findById(input)
        .orElseThrow(VehicleNotFoundException::new);
    return mapper.toResponseDto(foundVehicle);
  }
}