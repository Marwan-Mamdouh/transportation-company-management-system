package com.travelSafe.buses.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.trip.TripRepository;
import com.travelSafe.buses.exceptions.trip.TripNotFoundException;
import com.travelSafe.buses.domain.seats.model.Seat;
import com.travelSafe.buses.domain.travelLine.model.TravelLine;
import com.travelSafe.buses.domain.trip.model.Trip;
import com.travelSafe.buses.domain.trip.service.GetTripService;
import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetTripServiceTest {

  @Mock
  private TripRepository tripRepository;

  @InjectMocks
  private GetTripService getTripService;

  @Test
  public void gavin_trip_exist_when_get_trip_called_return_dto() {
    // gavin
    final Integer tripId = 1;
    final Trip trip = new Trip(tripId, new Vehicle(), new TravelLine(), new Employee(), 250,
        LocalDateTime.now(), List.of(new Seat()));

    // when
    when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));
    final Trip response = getTripService.execute(tripId);

    // then
    assertEquals(trip, response);
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