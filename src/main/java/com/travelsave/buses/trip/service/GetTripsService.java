package com.travelsave.buses.trip.service;

import com.travelsave.buses.Query;
import com.travelsave.buses.trip.TripRepository;
import com.travelsave.buses.trip.model.Trip;
import com.travelsave.buses.trip.model.TripDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetTripsService implements Query<Void, List<TripDTO>> {

  private final TripRepository tripRepository;

  public GetTripsService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  public ResponseEntity<List<TripDTO>> execute(Void input) {
    List<Trip> trips = tripRepository.findAll();
    List<TripDTO> tripDTOS = trips.stream().map(TripDTO::new).toList();
    return ResponseEntity.ok(tripDTOS);
  }
}