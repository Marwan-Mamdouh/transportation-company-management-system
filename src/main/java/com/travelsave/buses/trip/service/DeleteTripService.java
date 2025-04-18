package com.travelsave.buses.trip.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.exceptions.trip.TripNotFoundException;
import com.travelsave.buses.trip.TripRepository;
import com.travelsave.buses.trip.model.Trip;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteTripService implements Command<Integer, Void> {

  private final TripRepository tripRepository;

  public DeleteTripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Integer input) {
    Optional<Trip> optionalTrip = tripRepository.findById(input);
    if (optionalTrip.isPresent()) {
      tripRepository.deleteById(input);
      return ResponseEntity.noContent().build();
    }
    throw new TripNotFoundException();
  }
}