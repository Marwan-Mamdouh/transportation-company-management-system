package com.travel.safe.buses.domain.travelLine.dto;

import java.util.List;

public record TravelLineResponseDTO(String startFrom, String destination, List<String> stations) {

}