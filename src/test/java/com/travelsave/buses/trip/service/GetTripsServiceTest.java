package com.travelsave.buses.trip.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.trip.TripRepository;
import com.travelsave.buses.trip.model.Trip;
import com.travelsave.buses.trip.model.TripDTO;
import com.travelsave.buses.vehicle.model.Vehicle;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetTripsServiceTest {

  @Mock
  private TripRepository tripRepository;

  @InjectMocks
  private GetTripsService getTripsService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_trips_exists_when_get_trip_called_return_dto() {
    // gavin
    final Trip trip1 = new Trip(1, new Vehicle(), new TravelLine(), new Employee(), "VIP", 250.0,
        LocalDate.parse("2025-05-15"));
    final Trip trip2 = new Trip(6, new Vehicle(), null, new Employee(), "ECO", 230.0,
        LocalDate.parse("2025-05-15"));
    final List<Trip> trips = List.of(trip1, trip2);

    // when
    when(tripRepository.findAll()).thenReturn(trips);
    final ResponseEntity<List<TripDTO>> response = getTripsService.execute(null);
    final List<TripDTO> tripDTOS = trips.stream().map(TripDTO::new).toList();

    // then
    assertEquals(ResponseEntity.ok(tripDTOS), response);
    verify(tripRepository, times(1)).findAll();
  }

  @Test
  public void gavin_trips_does_not_exists_when_get_trip_called_throw_not_found() {
    // gavin & when
    when(tripRepository.findAll()).thenReturn(List.of());

    // then
    assertEquals(ResponseEntity.ok(List.of()), getTripsService.execute(null));
    verify(tripRepository, times(1)).findAll();
  }
}