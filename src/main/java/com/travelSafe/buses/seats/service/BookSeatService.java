package com.travelSafe.buses.seats.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.employee.services.get.GetEmployeeService;
import com.travelSafe.buses.exceptions.seat.SeatAlreadyBookedException;
import com.travelSafe.buses.seats.SeatsRepository;
import com.travelSafe.buses.seats.model.dto.BookSeatDTO;
import com.travelSafe.buses.trip.model.Trip;
import com.travelSafe.buses.trip.service.GetTripService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookSeatService implements Command<BookSeatDTO, BookSeatDTO> {

  private static final Logger logger = LoggerFactory.getLogger(BookSeatService.class);
  private final SeatsRepository tripSeatRepository;
  private final GetEmployeeService getEmployeeService;
  private final GetTripService getTripService;

  public BookSeatService(SeatsRepository tripSeatRepository,
      GetEmployeeService getEmployeeService, GetTripService getTripService) {
    this.tripSeatRepository = tripSeatRepository;
    this.getEmployeeService = getEmployeeService;
    this.getTripService = getTripService;
  }

  @Override
  @Transactional
  public BookSeatDTO execute(BookSeatDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    final Trip trip = getTripService.execute(input.tripId());
    final Employee employee = getEmployeeService.execute(input.ssn());
    if (tripSeatRepository.bookSeatByEmployee(employee, LocalDateTime.now(), trip, input.seatNo())
        > 0) {
      return input;
    }
    throw new SeatAlreadyBookedException();
  }
}