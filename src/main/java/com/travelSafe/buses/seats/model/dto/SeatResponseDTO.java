package com.travelSafe.buses.seats.model.dto;

import com.travelSafe.buses.seats.model.Seat;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SeatResponseDTO {

  private final Integer tripId, seatNumber;

  public SeatResponseDTO(Seat seat) {
    this.tripId = seat.getTripId().getTripId();
    this.seatNumber = seat.getTripSeatId().getSeatNumber();
  }

  public SeatResponseDTO(BookSeatDTO seatDto) {
    this.tripId = seatDto.tripId();
    this.seatNumber = seatDto.seatNo();
  }
}