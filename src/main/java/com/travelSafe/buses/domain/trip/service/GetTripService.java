package com.travelSafe.buses.domin.trip.service;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.exceptions.trip.TripNotFoundException;
import com.travelSafe.buses.domin.trip.TripRepository;
import com.travelSafe.buses.domin.trip.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GetTripService implements Query<Integer, Trip> {

  private static final Logger logger = LoggerFactory.getLogger(GetTripService.class);
  private final TripRepository tripRepository;

  public GetTripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  @Cacheable("tripCache")
  public Trip execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return tripRepository.findById(input).orElseThrow(TripNotFoundException::new);
  }
}