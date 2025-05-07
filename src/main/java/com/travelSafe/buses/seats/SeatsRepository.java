package com.travelSafe.buses.seats;

import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.trip.model.Trip;
import com.travelSafe.buses.seats.model.Seat;
import com.travelSafe.buses.seats.model.SeatId;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, SeatId> {

  @Query("""
      SELECT t FROM Seat t WHERE t.bookedBy IS NULL
      AND DATE(t.tripId.travelDateAndTime) = :travelDate
      AND LOWER(t.tripId.travelLine.startFrom) = LOWER(:startFrom)
      AND LOWER(t.tripId.travelLine.destination) = LOWER(:destination)""")
  List<Seat> findAvailableSeatsForGavinSearch(@Param("startFrom") String startFrom,
      @Param("destination") String destination, @Param("travelDate") LocalDate travelDate);

  @Query("select t from Seat t where t.tripSeatId.tripId = :tripId and t.bookedBy is null")
  List<Seat> findByTripSeatId_TripIdAndBookedBy(@Param("tripId") Integer tripId);

  @Modifying
  @Transactional
  @Query("""
      UPDATE Seat ts SET ts.bookedBy = :employee, ts.orderTime = :time
      WHERE ts.tripId = :tripId AND ts.tripSeatId.seatNumber = :seatNo AND ts.bookedBy IS NULL""")
  int bookSeatByEmployee(@Param("employee") Employee employee, @Param("time") LocalDateTime time,
      @Param("tripId") Trip tripId, @Param("seatNo") Integer seatNo);
}