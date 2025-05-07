package com.travelSafe.buses.trip;

import com.travelSafe.buses.seats.model.dto.SearchFreeSeatsDto;
import com.travelSafe.buses.trip.model.DTO.CreateTripDTO;
import com.travelSafe.buses.trip.model.DTO.TripAvailabilityDTO;
import com.travelSafe.buses.trip.model.DTO.TripDto;
import com.travelSafe.buses.trip.model.DTO.UpdateTripDTO;
import com.travelSafe.buses.trip.model.Trip;
import com.travelSafe.buses.trip.model.projection.TripAvailabilityProjection;
import com.travelSafe.buses.trip.service.CreateTripService;
import com.travelSafe.buses.trip.service.DeleteTripService;
import com.travelSafe.buses.trip.service.SearchForTripsService;
import com.travelSafe.buses.trip.service.UpdateTripService;
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
@RequestMapping("/trip")
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
  public ResponseEntity<TripDto> createTrip(@Valid @RequestBody CreateTripDTO trip) {
    final Trip savedTrip = createTripService.execute(trip);
    return ResponseEntity.ok(new TripDto(savedTrip));
  }

  @PutMapping("/{tripId}")
  public ResponseEntity<TripDto> updateTrip(@RequestBody UpdateTripDTO trip) {
    final Trip updatedTrip = updateTripService.execute(trip);
    return ResponseEntity.ok(new TripDto(updatedTrip));
  }

//  @GetMapping("/{tripId}")
//  public ResponseEntity<TripDto> getTrip(@PathVariable Integer tripId) {
//    final Trip trip = getTripService.execute(tripId);
//    return ResponseEntity.ok(new TripDto(trip));
//  }

//  @GetMapping("/available/seats")
//  public ResponseEntity<List<TripSeatDTO>> searchForSeats(
//      @Valid @RequestBody SearchFreeSeatsDto info) {
//    final List<TripSeat> tripSeats = .execute(info);
//    return ResponseEntity.ok(tripSeats.stream().map(TripSeatDTO::new).toList());
//  }

  @GetMapping("/available/trips")
  public ResponseEntity<List<TripAvailabilityDTO>> searchForTrips(
      @Valid @RequestBody SearchFreeSeatsDto info) {
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