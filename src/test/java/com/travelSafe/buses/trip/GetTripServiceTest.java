package com.travelSafe.buses.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.exceptions.trip.TripNotFoundException;
import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.trip.model.Trip;
import com.travelSafe.buses.trip.model.DTO.TripDto;
import com.travelSafe.buses.trip.service.GetTripService;
import com.travelSafe.buses.seats.model.Seat;
import com.travelSafe.buses.vehicle.model.Vehicle;
import java.time.LocalDateTime;
import java.util.List;
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
  public void gavin_trip_exist_when_get_trip_called_return_dto() {
    // gavin
    final Integer tripId = 1;
    final Trip trip = new Trip(tripId, new Vehicle(), new TravelLine(), new Employee(), 250,
        LocalDateTime.now(), List.of(new Seat()));

    // when
    when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));
    final ResponseEntity<TripDto> response = getTripService.execute(tripId);

    // then
    assertEquals(ResponseEntity.ok(new TripDto(trip)), response);
    verify(tripRepository, times(1)).findById(tripId);
  }

  @Test
  public void gavin_trip_does_not_exist_when_get_trip_called_throw_not_found() {
    // gavin
    final int wantedId = 2;
    // when
    when(tripRepository.findById(wantedId)).thenReturn(Optional.empty());

    // then
    assertThrows(TripNotFoundException.class, () -> getTripService.execute(wantedId));
    verify(tripRepository, times(1)).findById(wantedId);
  }
}