package com.travel.safe.buses.domain.trip.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.trip.DTO.TripAvailabilityDTO;
import com.travel.safe.buses.domain.trip.DTO.TripSearchDTO;
import com.travel.safe.buses.domain.trip.TripMapper;
import com.travel.safe.buses.domain.trip.TripRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchForTripsService implements Query<TripSearchDTO, List<TripAvailabilityDTO>> {

  private static final Logger logger = LoggerFactory.getLogger(SearchForTripsService.class);
  private final TripRepository tripRepository;
  private final TripMapper mapper;

  public SearchForTripsService(TripRepository tripRepository, TripMapper mapper) {
    this.tripRepository = tripRepository;
    this.mapper = mapper;
  }

  @Override
  @Cacheable(value = "tripCache")
  public List<TripAvailabilityDTO> execute(TripSearchDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var foundTrips = tripRepository.findAvailableTripsForGavinSearch(input.startFrom(),
        input.destination(), input.travelDate());
    return foundTrips.stream().map(mapper::toResponseDto).toList();
  }
}