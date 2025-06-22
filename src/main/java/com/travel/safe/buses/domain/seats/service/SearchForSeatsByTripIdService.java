package com.travel.safe.buses.domain.seats.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.seats.SeatsRepository;
import com.travel.safe.buses.domain.seats.exceptions.NoAvailableSeatsFoundException;
import com.travel.safe.buses.domain.seats.model.Seat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchForSeatsByTripIdService implements Query<Integer, List<Seat>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchForSeatsByTripIdService.class);
  private final SeatsRepository tripSeatRepository;

  public SearchForSeatsByTripIdService(SeatsRepository tripSeatRepository) {
    this.tripSeatRepository = tripSeatRepository;
  }

  @Override
  @Cacheable("seatCache")
  public List<Seat> execute(Integer input) {
    LOGGER.debug("Executing: {} with input: {}", getClass(), input);
    final List<Seat> seats = tripSeatRepository.findByTripSeatId_TripIdAndBookedBy(input);

    if (seats.isEmpty()) {
      throw new NoAvailableSeatsFoundException();
    }
    return seats;
  }
}