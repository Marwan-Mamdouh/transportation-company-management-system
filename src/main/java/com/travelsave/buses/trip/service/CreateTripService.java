package com.travelsave.buses.trip.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.trip.TripRepository;
import com.travelsave.buses.trip.model.Trip;
import com.travelsave.buses.trip.model.TripDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateTripService implements Command<Trip, TripDTO> {

  private final TripRepository tripRepository;

  public CreateTripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  public ResponseEntity<TripDTO> execute(Trip input) {
    Trip savedTrip = tripRepository.save(input);
    return ResponseEntity.ok(new TripDTO(savedTrip));
  }
}