package com.travelSafe.buses.seats.service;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.seats.SeatsRepository;
import com.travelSafe.buses.seats.model.Seat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SearchForSeatsByTripId implements Query<Integer, List<Seat>> {

  private static final Logger logger = LoggerFactory.getLogger(SearchForSeatsByTripId.class);
  private final SeatsRepository tripSeatRepository;

  public SearchForSeatsByTripId(SeatsRepository tripSeatRepository) {
    this.tripSeatRepository = tripSeatRepository;
  }

  @Override
  public List<Seat> execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return tripSeatRepository.findByTripSeatId_TripIdAndBookedBy(input);
  }
}