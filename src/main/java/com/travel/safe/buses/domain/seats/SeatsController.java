package com.travel.safe.buses.domain.seats;

import com.travel.safe.buses.domain.seats.dto.BookSeatDTO;
import com.travel.safe.buses.domain.seats.dto.SeatResponseDTO;
import com.travel.safe.buses.domain.seats.service.BookSeatService;
import com.travel.safe.buses.domain.seats.service.SearchForSeatsByTripIdService;
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
@RequestMapping("/api/seats")
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
    return ResponseEntity.ok(searchForSeatsInByTripStartAndTo.execute(tripId));
  }

  @PostMapping("/book")
  public ResponseEntity<SeatResponseDTO> bookSeat(@Valid @RequestBody BookSeatDTO bookSeatDto) {
    return ResponseEntity.ok(bookSeatService.execute(bookSeatDto));
  }
}