package com.travel.safe.buses.domain.trip.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.trip.DTO.TripResponseDTO;
import com.travel.safe.buses.domain.trip.DTO.UpdateTripDTO;
import com.travel.safe.buses.domain.trip.TripMapper;
import com.travel.safe.buses.domain.trip.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class UpdateTripService implements Command<UpdateTripDTO, TripResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateTripService.class);
  private final TripRepository tripRepository;
  private final GetTripService getTripService;
  private final TripMapper mapper;

  public UpdateTripService(TripRepository tripRepository, GetTripService getTripService,
      TripMapper tripMapper) {
    this.tripRepository = tripRepository;
    this.getTripService = getTripService;
    this.mapper = tripMapper;
  }

  @Override
  @CachePut(value = "tripCache", key = "#input.tripId()")
  public TripResponseDTO execute(UpdateTripDTO input) {
    logger.debug("Executing: {} with input:{}", getClass(), input);
    getTripService.execute(input.tripId());
    final var updatedTrip = tripRepository.save(mapper.toEntity(input));
    return mapper.toResponseDto(updatedTrip);
  }
}