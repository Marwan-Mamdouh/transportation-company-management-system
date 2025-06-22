package com.travel.safe.buses.seats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.seats.SeatsRepository;
import com.travel.safe.buses.domain.seats.exceptions.NoAvailableSeatsFoundException;
import com.travel.safe.buses.domain.seats.model.Seat;
import com.travel.safe.buses.domain.seats.model.SeatId;
import com.travel.safe.buses.domain.seats.service.SearchForSeatsByTripIdService;
import com.travel.safe.buses.domain.trip.model.Trip;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchForSeatsByTripIdServiceTest {

  @Mock
  private SeatsRepository seatsRepository;

  @InjectMocks
  private SearchForSeatsByTripIdService search;

  @Test
  void givenSeatsListExistsAndBooked_whenSearchSeatsService_returnEmptyList() {
    // given
    final Integer tripId = 3;
    final Trip trip = new Trip();
    trip.setTripId(tripId);
    final Employee employee = new Employee();
    employee.setSsn(30123243235723L);

    // when
    when(seatsRepository.findByTripSeatId_TripIdAndBookedBy(tripId)).thenReturn(List.of());
    // then
    assertThrows(NoAvailableSeatsFoundException.class, () -> search.execute(tripId));
    verify(seatsRepository, times(1)).findByTripSeatId_TripIdAndBookedBy(tripId);
  }

  @Test
  void givenSeatsListExistsAndAvailable_whenSearchSeatsService_returnSeatList() {
    // given
    final Integer tripId = 3;
    final Trip trip = new Trip();
    trip.setTripId(tripId);
    final Seat seat1 = new Seat(new SeatId(42, 3), trip, null, null);
    final Seat seat2 = new Seat(new SeatId(43, 3), trip, null, null);
    final Seat seat3 = new Seat(new SeatId(44, 3), trip, null, null);
    final Seat seat4 = new Seat(new SeatId(45, 3), trip, null, null);
    final List<Seat> seats = List.of(seat1, seat2, seat3, seat4);
    // when
    when(seatsRepository.findByTripSeatId_TripIdAndBookedBy(tripId)).thenReturn(seats);
    final List<Seat> response = search.execute(tripId);
    // then
    assertEquals(seats, response);
    verify(seatsRepository, times(1)).findByTripSeatId_TripIdAndBookedBy(tripId);
  }

  @Test
  void givenSeatsListExistsAndSomeFreeToBook_whenSearchSeatsService_returnSeatsList() {
    // given
    final Integer tripId = 3;
    final Trip trip = new Trip();
    trip.setTripId(tripId);
    final Employee employee = new Employee();
    employee.setSsn(30123243235723L);
    final Seat seat3 = new Seat(new SeatId(44, 3), trip, null, null);
    final Seat seat4 = new Seat(new SeatId(45, 3), trip, null, null);
    final List<Seat> freeSeats = List.of(seat3, seat4);

    // when
    when(seatsRepository.findByTripSeatId_TripIdAndBookedBy(tripId)).thenReturn(freeSeats);
    final List<Seat> response = search.execute(tripId);
    // then
    assertEquals(freeSeats, response);
    verify(seatsRepository, times(1)).findByTripSeatId_TripIdAndBookedBy(tripId);
  }

  @Test
  void givenSeatsListDoesNotExists_whenSearchSeatsService_returnEmptyList() {
    // given
    final Integer tripId = 3;
    final Trip trip = new Trip();
    trip.setTripId(tripId);
    final List<Seat> emptyList = List.of();

    // when
    when(seatsRepository.findByTripSeatId_TripIdAndBookedBy(tripId)).thenReturn(emptyList);
    // then
    assertThrows(NoAvailableSeatsFoundException.class, () -> search.execute(tripId));
    verify(seatsRepository, times(1)).findByTripSeatId_TripIdAndBookedBy(tripId);
  }
}