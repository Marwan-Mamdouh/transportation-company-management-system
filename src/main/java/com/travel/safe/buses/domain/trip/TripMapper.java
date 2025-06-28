package com.travel.safe.buses.domain.trip;

import com.travel.safe.buses.domain.trip.DTO.CreateTripDTO;
import com.travel.safe.buses.domain.trip.DTO.TripAvailabilityDTO;
import com.travel.safe.buses.domain.trip.DTO.TripResponseDTO;
import com.travel.safe.buses.domain.trip.DTO.UpdateTripDTO;
import com.travel.safe.buses.domain.trip.model.Trip;
import com.travel.safe.buses.domain.trip.model.TripAvailabilityProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

  TripResponseDTO toResponseDto(Trip trip);

  @Mapping(target = "availableSeatCount", source = "seatCount")
  TripAvailabilityDTO toResponseDto(TripAvailabilityProjection trip);


  @Mapping(target = "tripId", ignore = true)
  @Mapping(target = "seats", ignore = true)
  @Mapping(target = "driver.ssn", source = "driver")
  @Mapping(target = "car.vehicleId", source = "car")
  @Mapping(target = "travelLine.lineId", source = "travelLine")
  Trip toEntity(CreateTripDTO tripDTO);


  @Mapping(target = "seats", ignore = true)
  @Mapping(target = "driver.ssn", source = "driver")
  @Mapping(target = "car.vehicleId", source = "car")
  @Mapping(target = "travelLine.lineId", source = "travelLine")
  Trip toEntity(UpdateTripDTO tripDTO);
}