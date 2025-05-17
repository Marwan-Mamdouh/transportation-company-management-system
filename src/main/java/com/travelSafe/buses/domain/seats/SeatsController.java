package com.travelSafe.buses.domain.seats;

import com.travelSafe.buses.domain.seats.model.Seat;
import com.travelSafe.buses.domain.seats.model.dto.BookSeatDTO;
import com.travelSafe.buses.domain.seats.model.dto.SeatResponseDTO;
import com.travelSafe.buses.domain.seats.service.BookSeatService;
import com.travelSafe.buses.domain.seats.service.SearchForSeatsByTripIdService;
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

  private final SearchForSeatsByTripIdService searchForSeatsInByTripStartAndTo;
  private final BookSeatService bookSeatService;

  public SeatsController(SearchForSeatsByTripIdService searchForSeatsInByTripStartAndTo,
      BookSeatService bookSeatService) {
    this.searchForSeatsInByTripStartAndTo = searchForSeatsInByTripStartAndTo;
    this.bookSeatService = bookSeatService;
  }

  @GetMapping("/free/{tripId}")
  public ResponseEntity<List<SeatResponseDTO>> findAvailableSeatsByTrip(
      @PathVariable Integer tripId) {
    final List<Seat> seats = searchForSeatsInByTripStartAndTo.execute(tripId);
    return ResponseEntity.ok(seats.stream().map(SeatResponseDTO::new).toList());
  }

  @PostMapping("/book")
  public ResponseEntity<SeatResponseDTO> bookSeat(@Valid @RequestBody BookSeatDTO bookSeatDto) {
    final Seat seat = bookSeatService.execute(bookSeatDto);
    return ResponseEntity.ok(new SeatResponseDTO(seat));
  }
}