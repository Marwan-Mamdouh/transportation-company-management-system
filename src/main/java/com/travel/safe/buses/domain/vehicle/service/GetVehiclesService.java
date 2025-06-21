package com.travel.safe.buses.domain.vehicle.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetVehiclesService implements Query<Void, List<Vehicle>> {

  private static final Logger logger = LoggerFactory.getLogger(GetVehiclesService.class);
  private final VehicleRepository vehicleRepository;

  public GetVehiclesService(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public List<Vehicle> execute(Void input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return vehicleRepository.findAll();
  }
}