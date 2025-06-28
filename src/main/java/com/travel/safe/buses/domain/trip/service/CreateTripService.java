package com.travel.safe.buses.domain.trip.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.exceptions.TravelLineNotFoundException;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import com.travel.safe.buses.domain.trip.DTO.CreateTripDTO;
import com.travel.safe.buses.domain.trip.DTO.TripResponseDTO;
import com.travel.safe.buses.domain.trip.TripMapper;
import com.travel.safe.buses.domain.trip.TripRepository;
import com.travel.safe.buses.domain.trip.model.Trip;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.exceptions.VehicleNotFoundException;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateTripService implements Command<CreateTripDTO, TripResponseDTO> {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateTripService.class);
  private final TripMapper mapper;
  private final TripRepository tripRepository;
  private final VehicleRepository vehicleRepository;
  private final EmployeeRepository employeeRepository;
  private final TravelLineRepository travelLineRepository;

  public CreateTripService(TripRepository tripRepository, TripMapper tripMapper,
      VehicleRepository vehicleRepository, EmployeeRepository employeeRepository,
      TravelLineRepository travelLineRepository) {
    this.mapper = tripMapper;
    this.tripRepository = tripRepository;
    this.vehicleRepository = vehicleRepository;
    this.employeeRepository = employeeRepository;
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  @Transactional
  public TripResponseDTO execute(CreateTripDTO input) {
    LOGGER.debug("Executing: {} with input: {}", getClass(), input);
    // check and validate
    final Vehicle vehicle = vehicleRepository.findById(input.car())
        .orElseThrow(VehicleNotFoundException::new);
    final var employee = employeeRepository.findById(input.driver())
        .orElseThrow(EmployeeNotFoundException::new);
    final var travelLine = travelLineRepository.findById(input.travelLine())
        .orElseThrow(TravelLineNotFoundException::new);
    // map and build
    final var trip = mapAndBuildTrip(input, vehicle, employee, travelLine);
    // save
    final var savedTrip = tripRepository.save(trip);
    return mapper.toResponseDto(savedTrip);
  }

  private Trip mapAndBuildTrip(CreateTripDTO input, Vehicle vehicle, Employee employee,
      TravelLine travelLine) {
    final Trip trip = mapper.toEntity(input);
    trip.setCar(vehicle);
    trip.setDriver(employee);
    trip.setTravelLine(travelLine);
    return trip;
  }
}