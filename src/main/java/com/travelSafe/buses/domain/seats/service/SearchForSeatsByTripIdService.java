package com.travelSafe.buses.domain.seats.service;

import com.travelSafe.buses.comman.shared.Query;
import com.travelSafe.buses.domain.seats.SeatsRepository;
import com.travelSafe.buses.domain.seats.model.Seat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchForSeatsByTripIdService implements Query<Integer, List<Seat>> {

  private static final Logger logger = LoggerFactory.getLogger(SearchForSeatsByTripIdService.class);
  private final SeatsRepository tripSeatRepository;

  public SearchForSeatsByTripIdService(SeatsRepository tripSeatRepository) {
    this.tripSeatRepository = tripSeatRepository;
  }

  @Override
  @Cacheable("seatCache")
  public List<Seat> execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return tripSeatRepository.findByTripSeatId_TripIdAndBookedBy(input);
  }
}