package com.travelsave.buses.trip.model;

public record UpdateTripCommand(Integer tripId, Trip updatedTrip) {

}
