package com.travelSafe.buses.domain.trip.model.projection;

import com.travelSafe.buses.domain.trip.model.Trip;

public interface TripAvailabilityProjection {

  Trip getTrip();

  Long getSeatCount();
}