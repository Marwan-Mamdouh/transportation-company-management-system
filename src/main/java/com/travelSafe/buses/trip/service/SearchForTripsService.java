package com.travelSafe.buses.trip.service;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.seats.model.dto.SearchFreeSeatsDTO;
import com.travelSafe.buses.trip.TripRepository;
import com.travelSafe.buses.trip.model.projection.TripAvailabilityProjection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SearchForTripsService implements
    Query<SearchFreeSeatsDTO, List<TripAvailabilityProjection>> {

  private final TripRepository tripRepository;

  private static final Logger logger = LoggerFactory.getLogger(SearchForTripsService.class);

  public SearchForTripsService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  public List<TripAvailabilityProjection> execute(SearchFreeSeatsDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return tripRepository.findAvailableTripsForGavinSearch(input.startFrom(), input.destination(),
        input.travelDate());
  }
}