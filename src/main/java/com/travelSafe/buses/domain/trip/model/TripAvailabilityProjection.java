package com.travelSafe.buses.domain.trip.model;

public interface TripAvailabilityProjection {

  Trip getTrip();

  Long getSeatCount();
}