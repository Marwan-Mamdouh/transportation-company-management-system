package com.travel.safe.buses.domain.seats.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.employee.services.get.GetEmployeeService;
import com.travel.safe.buses.domain.seats.SeatsRepository;
import com.travel.safe.buses.domain.seats.dto.BookSeatDTO;
import com.travel.safe.buses.domain.seats.exceptions.SeatAlreadyBookedException;
import com.travel.safe.buses.domain.seats.model.Seat;
import com.travel.safe.buses.domain.trip.model.Trip;
import com.travel.safe.buses.domain.trip.service.GetTripService;
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
    logger.debug("Executing: {} with input: {}", getClass(), input);

    final Trip trip = getTripService.execute(input.tripId());
    final Employee employee = getEmployeeService.execute(input.ssn());

    if (seatRepository.bookSeatByEmployee(employee, LocalDateTime.now(), trip, input.seatNo())
        > 0) {
      return seatRepository.findById(input.toSeatId()).orElseThrow();
    }
    throw new SeatAlreadyBookedException();
  }
}