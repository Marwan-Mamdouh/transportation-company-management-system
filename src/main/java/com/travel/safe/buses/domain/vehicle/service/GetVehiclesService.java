package com.travel.safe.buses.domain.vehicle.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.vehicle.VehicleMapper;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.dto.VehicleResponseDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetVehiclesService implements Query<Void, List<VehicleResponseDTO>> {

  private static final Logger logger = LoggerFactory.getLogger(GetVehiclesService.class);
  private final VehicleRepository vehicleRepository;
  private final VehicleMapper mapper;

  public GetVehiclesService(VehicleRepository vehicleRepository, VehicleMapper mapper) {
    this.vehicleRepository = vehicleRepository;
    this.mapper = mapper;
  }

  @Override
  public List<VehicleResponseDTO> execute(Void input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var vehicles = vehicleRepository.findAll();
    return vehicles.stream().map(mapper::toResponseDto).toList();
  }
}