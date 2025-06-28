package com.travel.safe.buses.domain.trip.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import com.travel.safe.buses.domain.travelLine.service.GetTravelLineService;
import com.travel.safe.buses.domain.trip.DTO.CreateTripDTO;
import com.travel.safe.buses.domain.trip.TripMapper;
import com.travel.safe.buses.domain.trip.TripRepository;
import com.travel.safe.buses.domain.trip.model.Trip;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import com.travel.safe.buses.domain.vehicle.service.GetVehicleService;
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
  private final EmployeeRepository employeeRepository;
  private final GetTravelLineService getTravelLineService;

  public CreateTripService(TripRepository tripRepository, TripMapper tripMapper,
      GetVehicleService getVehicleService, EmployeeRepository employeeRepository,
      GetTravelLineService getTravelLineService) {
    this.tripRepository = tripRepository;
    this.tripMapper = tripMapper;
    this.getVehicleService = getVehicleService;
    this.employeeRepository = employeeRepository;
    this.getTravelLineService = getTravelLineService;
  }

  @Override
  @Transactional
  public Trip execute(CreateTripDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    // check and validate
    final Vehicle vehicle = getVehicleService.execute(input.car());
    final var employee = employeeRepository.findById(input.driver()).orElseThrow(
        EmployeeNotFoundException::new);
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