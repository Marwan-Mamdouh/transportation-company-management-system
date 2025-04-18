package com.travelsave.buses.trip.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.exceptions.trip.TripNotFoundException;
import com.travelsave.buses.trip.TripRepository;
import com.travelsave.buses.trip.model.Trip;
import com.travelsave.buses.trip.model.TripDTO;
import com.travelsave.buses.trip.model.UpdateTripCommand;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateTripService implements Command<UpdateTripCommand, TripDTO> {

  private final TripRepository tripRepository;

  public UpdateTripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  public ResponseEntity<TripDTO> execute(UpdateTripCommand input) {
    Optional<Trip> optionalTrip = tripRepository.findById(input.tripId());
    if (optionalTrip.isPresent()) {
      Trip updatedTrip = tripRepository.save(input.updatedTrip());
      return ResponseEntity.ok(new TripDTO(updatedTrip));
    }
    throw new TripNotFoundException();
  }
}
