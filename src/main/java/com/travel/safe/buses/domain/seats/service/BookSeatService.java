package com.travel.safe.buses.domain.seats.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.employee.EmployeeRepository;
import com.travel.safe.buses.domain.employee.exceptions.EmployeeNotFoundException;
import com.travel.safe.buses.domain.seats.SeatMapper;
import com.travel.safe.buses.domain.seats.SeatsRepository;
import com.travel.safe.buses.domain.seats.dto.BookSeatDTO;
import com.travel.safe.buses.domain.seats.dto.SeatResponseDTO;
import com.travel.safe.buses.domain.seats.exceptions.SeatAlreadyBookedException;
import com.travel.safe.buses.domain.trip.TripRepository;
import com.travel.safe.buses.domain.trip.exceptions.TripNotFoundException;
import com.travel.safe.buses.domain.trip.model.Trip;
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
  private final TripRepository tripRepository;
  private final SeatMapper mapper;

  public BookSeatService(SeatsRepository tripSeatRepository, EmployeeRepository employeeRepository,
      TripRepository tripRepository, SeatMapper mapper) {
    this.employeeRepository = employeeRepository;
    this.seatRepository = tripSeatRepository;
    this.tripRepository = tripRepository;
    this.mapper = mapper;
  }

  @Override
  @Transactional
  @CachePut(value = "seatCache", key = "#input")
  public SeatResponseDTO execute(BookSeatDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);

    final Trip trip = tripRepository.findById(input.tripId())
        .orElseThrow(TripNotFoundException::new);
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