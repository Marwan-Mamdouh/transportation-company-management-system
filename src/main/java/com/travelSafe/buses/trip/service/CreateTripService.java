package com.travelSafe.buses.trip.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.employee.services.get.GetEmployeeService;
import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.travelLine.service.GetTravelLineService;
import com.travelSafe.buses.trip.TripRepository;
import com.travelSafe.buses.trip.model.DTO.CreateTripDTO;
import com.travelSafe.buses.trip.model.Trip;
import com.travelSafe.buses.trip.model.TripMapper;
import com.travelSafe.buses.vehicle.model.Vehicle;
import com.travelSafe.buses.vehicle.service.GetVehicleService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateTripService implements Command<CreateTripDTO, Trip> {

  private static final Logger logger = LoggerFactory.getLogger(CreateTripService.class);
  private final TripMapper tripMapper;
  private final TripRepository tripRepository;
  private final GetVehicleService getVehicleService;
  private final GetEmployeeService getEmployeeService;
  private final GetTravelLineService getTravelLineService;

  public CreateTripService(TripRepository tripRepository, TripMapper tripMapper,
      GetVehicleService getVehicleService, GetEmployeeService getEmployeeService,
      GetTravelLineService getTravelLineService) {
    this.tripRepository = tripRepository;
    this.tripMapper = tripMapper;
    this.getVehicleService = getVehicleService;
    this.getEmployeeService = getEmployeeService;
    this.getTravelLineService = getTravelLineService;
  }

  @Override
  @Transactional
  public Trip execute(CreateTripDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    // check and validate
    final Vehicle vehicle = getVehicleService.execute(input.car());
    final Employee employee = getEmployeeService.execute(input.driver());
    final TravelLine travelLine = getTravelLineService.execute(input.travelLine());
    // map and build
    final Trip trip = tripMapper.toEntity(input);
    trip.setCar(vehicle);
    trip.setDriver(employee);
    trip.setTravelLine(travelLine);
    // save
    return tripRepository.save(trip);
  }
}