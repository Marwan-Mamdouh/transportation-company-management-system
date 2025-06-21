package com.travel.safe.buses.domain.trip;

import com.travel.safe.buses.domain.trip.DTO.CreateTripDTO;
import com.travel.safe.buses.domain.trip.DTO.TripAvailabilityDTO;
import com.travel.safe.buses.domain.trip.DTO.TripResponseDTO;
import com.travel.safe.buses.domain.trip.DTO.TripSearchDTO;
import com.travel.safe.buses.domain.trip.DTO.UpdateTripDTO;
import com.travel.safe.buses.domain.trip.model.Trip;
import com.travel.safe.buses.domain.trip.model.TripAvailabilityProjection;
import com.travel.safe.buses.domain.trip.service.CreateTripService;
import com.travel.safe.buses.domain.trip.service.DeleteTripService;
import com.travel.safe.buses.domain.trip.service.SearchForTripsService;
import com.travel.safe.buses.domain.trip.service.UpdateTripService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trip")
public class TripController {

  private final CreateTripService createTripService;

  private final DeleteTripService deleteTripService;

  private final SearchForTripsService searchForTripsService;

  private final UpdateTripService updateTripService;

  public TripController(CreateTripService createTripService, DeleteTripService deleteTripService,
      SearchForTripsService searchForTripsService, UpdateTripService updateTripService) {
    this.createTripService = createTripService;
    this.deleteTripService = deleteTripService;
    this.searchForTripsService = searchForTripsService;
    this.updateTripService = updateTripService;
  }

  @PostMapping
  public ResponseEntity<TripResponseDTO> createTrip(@Valid @RequestBody CreateTripDTO trip) {
    final Trip savedTrip = createTripService.execute(trip);
    return ResponseEntity.ok(new TripResponseDTO(savedTrip));
  }

  @PutMapping("/{tripId}")
  public ResponseEntity<TripResponseDTO> updateTrip(@Valid @RequestBody UpdateTripDTO trip) {
    final Trip updatedTrip = updateTripService.execute(trip);
    return ResponseEntity.ok(new TripResponseDTO(updatedTrip));
  }

  @GetMapping("/available")
  public ResponseEntity<List<TripAvailabilityDTO>> searchForTrips(
      @Valid @RequestBody TripSearchDTO info) {
    final List<TripAvailabilityProjection> tripSeats = searchForTripsService.execute(info);
    return ResponseEntity.ok(
        tripSeats.stream().map(trip -> new TripAvailabilityDTO(trip.getTrip(), trip.getSeatCount()))
            .toList());
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteTrip(@RequestBody Integer tripId) {
    return ResponseEntity.ok(deleteTripService.execute(tripId));
  }
}