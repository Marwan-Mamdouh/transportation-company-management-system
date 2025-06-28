package com.travel.safe.buses.domain.trip.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.trip.DTO.TripResponseDTO;
import com.travel.safe.buses.domain.trip.TripMapper;
import com.travel.safe.buses.domain.trip.TripRepository;
import com.travel.safe.buses.domain.trip.exceptions.TripNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GetTripService implements Query<Integer, TripResponseDTO> {

  private static final Logger LOGGER = LoggerFactory.getLogger(GetTripService.class);
  private final TripRepository tripRepository;
  private final TripMapper mapper;

  public GetTripService(TripRepository tripRepository, TripMapper mapper) {
    this.tripRepository = tripRepository;
    this.mapper = mapper;
  }

  @Override
  @Cacheable("tripCache")
  public TripResponseDTO execute(Integer input) {
    LOGGER.debug("Executing: {} with input: {}", getClass(), input);
    final var foundTrip = tripRepository.findById(input).orElseThrow(TripNotFoundException::new);
    return mapper.toResponseDto(foundTrip);
  }
}