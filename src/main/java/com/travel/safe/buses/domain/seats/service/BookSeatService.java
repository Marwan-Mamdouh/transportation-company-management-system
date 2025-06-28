package com.travel.safe.buses.domain.seats.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import com.travel.safe.buses.domain.seats.SeatMapper;
import com.travel.safe.buses.domain.seats.SeatsRepository;
import com.travel.safe.buses.domain.seats.dto.BookSeatDTO;
import com.travel.safe.buses.domain.seats.dto.SeatResponseDTO;
import com.travel.safe.buses.domain.seats.exceptions.SeatAlreadyBookedException;
import com.travel.safe.buses.domain.trip.model.Trip;
import com.travel.safe.buses.domain.trip.service.GetTripService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class BookSeatService implements Command<BookSeatDTO, SeatResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(BookSeatService.class);
  private final EmployeeRepository employeeRepository;
  private final SeatsRepository seatRepository;
  private final GetTripService getTripService;
  private final SeatMapper mapper;

  public BookSeatService(SeatsRepository tripSeatRepository, EmployeeRepository employeeRepository,
      GetTripService getTripService, SeatMapper mapper) {
    this.employeeRepository = employeeRepository;
    this.seatRepository = tripSeatRepository;
    this.getTripService = getTripService;
    this.mapper = mapper;
  }

  @Override
  @Transactional
  @CachePut(value = "seatCache", key = "#input")
  public SeatResponseDTO execute(BookSeatDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);

    final Trip trip = getTripService.execute(input.tripId());
    final var employee = employeeRepository.findById(input.ssn())
        .orElseThrow(EmployeeNotFoundException::new);

    if (seatRepository.bookSeatByEmployee(employee, LocalDateTime.now(), trip, input.seatNo())
        > 0) {
      final var seat = seatRepository.findById(mapper.toSeatId(input)).orElseThrow();
      return mapper.toSeatResponse(seat);
    }
    throw new SeatAlreadyBookedException();
  }
}