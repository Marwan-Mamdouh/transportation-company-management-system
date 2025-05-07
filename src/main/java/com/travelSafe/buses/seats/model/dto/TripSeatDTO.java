package com.travelSafe.buses.seats.model.dto;

import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.trip.model.Trip;
import com.travelSafe.buses.seats.model.Seat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TripSeatDTO {

  private final Integer seatNumber, ticketPrice;
  private final LocalDateTime travelDate;
  private final LocalDateTime orderTime;
  private final String from, to;

  public TripSeatDTO(Seat tripSeat) {
    this.seatNumber = tripSeat.getTripSeatId().getSeatNumber();
    this.orderTime = tripSeat.getOrderTime();
    final Trip trip = tripSeat.getTripId();
    this.ticketPrice = trip.getPrice();
    this.travelDate = trip.getTravelDateAndTime();
    final TravelLine travelLine = trip.getTravelLine();
    this.from = travelLine.getStartFrom();
    this.to = travelLine.getDestination();
  }
}