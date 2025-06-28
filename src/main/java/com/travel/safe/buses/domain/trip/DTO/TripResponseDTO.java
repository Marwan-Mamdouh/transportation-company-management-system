package com.travel.safe.buses.domain.trip.DTO;

import java.time.LocalDateTime;


public record TripResponseDTO(Integer tripId, Integer price, LocalDateTime travelDateAndTime) {

}