package com.travelSafe.buses.seats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.employee.model.Employee;
import com.travelSafe.buses.domain.seats.SeatsRepository;
import com.travelSafe.buses.domain.seats.model.Seat;
import com.travelSafe.buses.domain.seats.model.SeatId;
import com.travelSafe.buses.domain.seats.service.SearchForSeatsByTripIdService;
import com.travelSafe.buses.domain.trip.model.Trip;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchForSeatsByTripIdServiceTest {

  @Mock
  private SeatsRepository seatsRepository;

  @InjectMocks
  private SearchForSeatsByTripIdService search;

  @Test
  public void gavin_seats_list_exists_and_booked_when_search_service_called_return_list_of_seats_that_match() {
    // gavin
    final Integer tripId = 3;
    final Trip trip = new Trip();
    trip.setTripId(tripId);
    final Employee employee = new Employee();
    employee.setSsn(30123243235723L);
    final Seat seat1 = new Seat(new SeatId(42, 3), trip, employee, LocalDateTime.now());
    final Seat seat2 = new Seat(new SeatId(43, 3), trip, employee, LocalDateTime.now());
    final Seat seat3 = new Seat(new SeatId(44, 3), trip, employee, LocalDateTime.now());
    final Seat seat4 = new Seat(new SeatId(45, 3), trip, employee, LocalDateTime.now());
    final List<Seat> seats = List.of(seat1, seat2, seat3, seat4);
    // when
    when(seatsRepository.findByTripSeatId_TripIdAndBookedBy(tripId)).thenReturn(List.of());
    final List<Seat> response = search.execute(tripId);
    // then
    assertEquals(List.of(), response);
    verify(seatsRepository, times(1)).findByTripSeatId_TripIdAndBookedBy(tripId);
  }

  @Test
  public void gavin_seats_list_exists_and_free_to_book_when_search_service_called_return_list_of_seats_that_match() {
    // gavin
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
  public void gavin_seats_list_exists_and_some_free_to_book_when_search_service_called_return_list_of_seats_that_match() {
    // gavin
    final Integer tripId = 3;
    final Trip trip = new Trip();
    trip.setTripId(tripId);
    final Employee employee = new Employee();
    employee.setSsn(30123243235723L);
    final Seat seat1 = new Seat(new SeatId(42, 3), trip, employee, LocalDateTime.now());
    final Seat seat2 = new Seat(new SeatId(43, 3), trip, employee, LocalDateTime.now());
    final Seat seat3 = new Seat(new SeatId(44, 3), trip, null, null);
    final Seat seat4 = new Seat(new SeatId(45, 3), trip, null, null);
    final List<Seat> freeSeats = List.of(seat3, seat4);
    final List<Seat> bookedSeats = List.of(seat1, seat2);
    // when
    when(seatsRepository.findByTripSeatId_TripIdAndBookedBy(tripId)).thenReturn(freeSeats);
    final List<Seat> response = search.execute(tripId);
    // then
    assertEquals(freeSeats, response);
    verify(seatsRepository, times(1)).findByTripSeatId_TripIdAndBookedBy(tripId);
  }

  @Test
  public void gavin_seats_list_does_not_exists_when_search_service_called_return_list_of_seats_that_match() {
    // gavin
    final Integer tripId = 3;
    final Trip trip = new Trip();
    trip.setTripId(tripId);
    final List<Seat> emptyList = List.of();

    // when
    when(seatsRepository.findByTripSeatId_TripIdAndBookedBy(tripId)).thenReturn(emptyList);
    final List<Seat> response = search.execute(tripId);
    // then
    assertEquals(emptyList, response);
    verify(seatsRepository, times(1)).findByTripSeatId_TripIdAndBookedBy(tripId);
  }
}