package com.travel.safe.buses.domain.trip.model;

public interface TripAvailabilityProjection {

  Trip getTrip();

  Long getSeatCount();
}