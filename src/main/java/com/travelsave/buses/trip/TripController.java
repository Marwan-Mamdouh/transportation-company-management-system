package com.travelsave.buses.trip;

import com.travelsave.buses.trip.model.Trip;
import com.travelsave.buses.trip.model.TripDTO;
import com.travelsave.buses.trip.model.UpdateTripCommand;
import com.travelsave.buses.trip.service.CreateTripService;
import com.travelsave.buses.trip.service.DeleteTripService;
import com.travelsave.buses.trip.service.GetTripService;
import com.travelsave.buses.trip.service.GetTripsService;
import com.travelsave.buses.trip.service.UpdateTripService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {

  private final CreateTripService createTripService;

  private final DeleteTripService deleteTripService;

  private final GetTripService getTripService;
  private final GetTripsService getTripsService;

  private final UpdateTripService updateTripService;

  public TripController(CreateTripService createTripService, DeleteTripService deleteTripService,
      GetTripService getTripService, GetTripsService getTripsService,
      UpdateTripService updateTripService) {
    this.createTripService = createTripService;
    this.deleteTripService = deleteTripService;
    this.getTripService = getTripService;
    this.getTripsService = getTripsService;
    this.updateTripService = updateTripService;
  }

  @PostMapping("/trip")
  public ResponseEntity<TripDTO> createTrip(@RequestBody Trip trip) {
    return createTripService.execute(trip);
  }

  @PutMapping("/trip/{id}")
  public ResponseEntity<TripDTO> updateTrip(@PathVariable Integer id, @RequestBody Trip trip) {
    return updateTripService.execute(new UpdateTripCommand(id, trip));
  }

  @GetMapping("/trip/{id}")
  public ResponseEntity<TripDTO> getTrip(@PathVariable Integer id) {
    return getTripService.execute(id);
  }

  @GetMapping("/trips")
  public ResponseEntity<List<TripDTO>> getTrips() {
    return getTripsService.execute(null);
  }

  @DeleteMapping("/trip/{id}")
  public ResponseEntity<Void> deleteTrip(@PathVariable Integer id) {
    return deleteTripService.execute(id);
  }
}