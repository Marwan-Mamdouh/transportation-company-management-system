package com.travelSafe.buses.domin.seats.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domin.employee.model.Employee;
import com.travelSafe.buses.domin.employee.services.get.GetEmployeeService;
import com.travelSafe.buses.exceptions.seat.SeatAlreadyBookedException;
import com.travelSafe.buses.domin.seats.SeatsRepository;
import com.travelSafe.buses.domin.seats.model.Seat;
import com.travelSafe.buses.domin.seats.model.dto.BookSeatDTO;
import com.travelSafe.buses.domin.trip.model.Trip;
import com.travelSafe.buses.domin.trip.service.GetTripService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class BookSeatService implements Command<BookSeatDTO, Seat> {

  private static final Logger logger = LoggerFactory.getLogger(BookSeatService.class);
  private final GetEmployeeService getEmployeeService;
  private final SeatsRepository seatRepository;
  private final GetTripService getTripService;

  public BookSeatService(SeatsRepository tripSeatRepository, GetEmployeeService getEmployeeService,
      GetTripService getTripService) {
    this.getEmployeeService = getEmployeeService;
    this.seatRepository = tripSeatRepository;
    this.getTripService = getTripService;
  }

  @Override
  @Transactional
  @CachePut(value = "seatCache", key = "#input")
  public Seat execute(BookSeatDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    // check
    final Trip trip = getTripService.execute(input.tripId());
    final Employee employee = getEmployeeService.execute(input.ssn());
    // save
    if (seatRepository.bookSeatByEmployee(employee, LocalDateTime.now(), trip, input.seatNo())
        > 0) {
      return seatRepository.findById(input.toSeatId()).orElseThrow();
    }
    throw new SeatAlreadyBookedException();
  }
}