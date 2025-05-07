package com.travelSafe.buses.trip.model.projection;

import com.travelSafe.buses.trip.model.Trip;

public interface TripAvailabilityProjection {

  Trip getTrip();

  Long getSeatCount();
}