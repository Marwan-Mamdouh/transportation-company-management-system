package com.travel.safe.buses.domain.travelLine;

import com.travel.safe.buses.domain.travelLine.dto.InputTravelLineDTO;
import com.travel.safe.buses.domain.travelLine.dto.TravelLineResponseDTO;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TravelLineMapper {

  TravelLineResponseDTO toResponse(TravelLine travelLine);

  TravelLine toTravelLine(InputTravelLineDTO inputTravelLineDTO);
}