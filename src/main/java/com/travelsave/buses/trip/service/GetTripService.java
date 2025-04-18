package com.travelsave.buses.trip.service;

import com.travelsave.buses.Query;
import com.travelsave.buses.exceptions.trip.TripNotFoundException;
import com.travelsave.buses.trip.TripRepository;
import com.travelsave.buses.trip.model.Trip;
import com.travelsave.buses.trip.model.TripDTO;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetTripService implements Query<Integer, TripDTO> {

  private final TripRepository tripRepository;

  public GetTripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  public ResponseEntity<TripDTO> execute(Integer input) {
    Optional<Trip> optionalTrip = tripRepository.findById(input);
    if (optionalTrip.isPresent()) {
      return ResponseEntity.ok(new TripDTO(optionalTrip.get()));
    }
    throw new TripNotFoundException();
  }
}