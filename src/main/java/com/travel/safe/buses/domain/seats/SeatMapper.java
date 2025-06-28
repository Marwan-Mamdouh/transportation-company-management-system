package com.travel.safe.buses.domain.seats;

import com.travel.safe.buses.domain.seats.dto.BookSeatDTO;
import com.travel.safe.buses.domain.seats.dto.SeatResponseDTO;
import com.travel.safe.buses.domain.seats.model.Seat;
import com.travel.safe.buses.domain.seats.model.SeatId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {

  @Mapping(target = "tripId", source = "tripId.tripId")
  @Mapping(target = "seatNumber", source = "tripSeatId.seatNumber")
  SeatResponseDTO toSeatResponse(Seat seat);

  @Mapping(target = "seatNumber", source = "seatNo")
  SeatResponseDTO toSeatResponse(BookSeatDTO seatDto);

  @Mapping(target = "seatNumber", source = "seatNo")
  SeatId toSeatId(BookSeatDTO bookSeatDTO);
}