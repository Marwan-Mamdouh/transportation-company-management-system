package com.travelSafe.buses.seats;

import com.travelSafe.buses.seats.model.Seat;
import com.travelSafe.buses.seats.model.dto.BookSeatDto;
import com.travelSafe.buses.seats.model.dto.SeatDTO;
import com.travelSafe.buses.seats.service.BookSeatService;
import com.travelSafe.buses.seats.service.SearchForSeatsByTripId;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class SeatsController {

  private final SearchForSeatsByTripId searchForSeatsInByTripStartAndTo;
  private final BookSeatService bookSeatService;

  public SeatsController(SearchForSeatsByTripId searchForSeatsInByTripStartAndTo,
      BookSeatService bookSeatService) {
    this.searchForSeatsInByTripStartAndTo = searchForSeatsInByTripStartAndTo;
    this.bookSeatService = bookSeatService;
  }

  @GetMapping("/free/{tripId}")
  public ResponseEntity<List<SeatDTO>> findAvailableSeatsByTrip(@PathVariable Integer tripId) {
    final List<Seat> seats = searchForSeatsInByTripStartAndTo.execute(tripId);
    return ResponseEntity.ok(seats.stream().map(SeatDTO::new).toList());
  }

  @PostMapping("/book/seat")
  public ResponseEntity<SeatDTO> bookSeat(@Valid @RequestBody BookSeatDto bookSeatDto) {
    final BookSeatDto seat = bookSeatService.execute(bookSeatDto);
    return ResponseEntity.ok(new SeatDTO(seat));
  }
}