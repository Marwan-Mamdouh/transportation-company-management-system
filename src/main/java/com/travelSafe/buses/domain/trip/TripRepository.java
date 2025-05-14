package com.travelSafe.buses.domin.trip;

import com.travelSafe.buses.domin.trip.model.Trip;
import com.travelSafe.buses.domin.trip.model.projection.TripAvailabilityProjection;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

  @Query("""
      SELECT t AS trip, COUNT(s) AS seatCount FROM Trip t JOIN t.seats s WHERE s.bookedBy IS NULL
      AND DATE(t.travelDateAndTime) = :travelDate
      AND LOWER(t.travelLine.startFrom) = LOWER(:startFrom)
      AND LOWER(t.travelLine.destination) = LOWER(:destination) GROUP BY t""")
  List<TripAvailabilityProjection> findAvailableTripsForGavinSearch(
      @Param("startFrom") String startFrom, @Param("destination") String destination,
      @Param("travelDate") LocalDate travelDate);
}