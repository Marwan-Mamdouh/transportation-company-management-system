package com.travel.safe.buses.domain.trip.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.trip.DTO.TripSearchDTO;
import com.travel.safe.buses.domain.trip.TripRepository;
import com.travel.safe.buses.domain.trip.model.TripAvailabilityProjection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchForTripsService implements
    Query<TripSearchDTO, List<TripAvailabilityProjection>> {

  private static final Logger logger = LoggerFactory.getLogger(SearchForTripsService.class);
  private final TripRepository tripRepository;

  public SearchForTripsService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  @Cacheable(value = "tripCache")
  public List<TripAvailabilityProjection> execute(TripSearchDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    return tripRepository.findAvailableTripsForGavinSearch(input.startFrom(), input.destination(),
        input.travelDate());
  }
}