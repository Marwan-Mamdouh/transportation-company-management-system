package com.travel.safe.buses.domain.trip.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.trip.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteTripService implements Command<Integer, Void> {

  private static final Logger logger = LoggerFactory.getLogger(DeleteTripService.class);
  private final TripRepository tripRepository;
  private final GetTripService getTripService;

  public DeleteTripService(TripRepository tripRepository, GetTripService getTripService) {
    this.tripRepository = tripRepository;
    this.getTripService = getTripService;
  }

  @Override
  public Void execute(Integer input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    // check
    getTripService.execute(input);
    // do
    tripRepository.deleteById(input);
    // return
    return null;
  }
}