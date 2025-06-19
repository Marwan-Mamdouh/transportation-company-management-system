package com.travelSafe.buses.domain.trip.service;

import com.travelSafe.buses.comman.shared.Command;
import com.travelSafe.buses.domain.trip.DTO.UpdateTripDTO;
import com.travelSafe.buses.domain.trip.TripMapper;
import com.travelSafe.buses.domain.trip.TripRepository;
import com.travelSafe.buses.domain.trip.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class UpdateTripService implements Command<UpdateTripDTO, Trip> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateTripService.class);
  private final TripRepository tripRepository;
  private final GetTripService getTripService;
  private final TripMapper tripMapper;

  public UpdateTripService(TripRepository tripRepository, GetTripService getTripService,
      TripMapper tripMapper) {
    this.tripRepository = tripRepository;
    this.getTripService = getTripService;
    this.tripMapper = tripMapper;
  }

  @Override
  @CachePut(value = "tripCache", key = "#input.tripId()")
  public Trip execute(UpdateTripDTO input) {
    logger.info("Executing: {} with input:{}", getClass(), input);
    getTripService.execute(input.tripId());
    return tripRepository.save(tripMapper.toEntity(input));
  }
}