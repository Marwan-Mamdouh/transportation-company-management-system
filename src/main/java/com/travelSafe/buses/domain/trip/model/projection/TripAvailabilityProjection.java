package com.travelSafe.buses.domin.trip.model.projection;

import com.travelSafe.buses.domin.trip.model.Trip;

public interface TripAvailabilityProjection {

  Trip getTrip();

  Long getSeatCount();
}