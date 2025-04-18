package com.travelsave.buses.trip.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.exceptions.trip.TripNotFoundException;
import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.trip.TripRepository;
import com.travelsave.buses.trip.model.Trip;
import com.travelsave.buses.trip.model.TripDTO;
import com.travelsave.buses.vehicle.model.Vehicle;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetTripServiceTest {

  @Mock
  private TripRepository tripRepository;

  @InjectMocks
  private GetTripService getTripService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_trip_exists_when_get_trip_called_return_dto() {
    // gavin
    final Integer tripId = 1;
    final Trip trip = new Trip(tripId, new Vehicle(), new TravelLine(), new Employee(), "VIP", 250.0,
        LocalDate.parse("2025-05-15"));

    // when
    when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));
    final ResponseEntity<TripDTO> response = getTripService.execute(tripId);

    // then
    assertEquals(ResponseEntity.ok(new TripDTO(trip)), response);
    verify(tripRepository, times(1)).findById(tripId);
  }

  @Test
  public void gavin_trip_does_not_exists_when_get_trip_called_throw_not_found() {
    // gavin
    final int wantedId = 2;
    // when
    when(tripRepository.findById(wantedId)).thenReturn(Optional.empty());

    // then
    assertThrows(TripNotFoundException.class, () -> getTripService.execute(wantedId));
    verify(tripRepository, times(1)).findById(wantedId);
  }
}