package com.travelSafe.buses.domain.travelLine.model.dto;

import com.travelSafe.buses.domain.travelLine.model.TravelLine;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.List;

public record InputTravelLineDTO(@NotNull @Positive @Max(255) Integer lineId,
                                 @NotBlank @Size(max = 55, message = "station name must be 55 characters max.") String startFrom,
                                 @NotBlank @Size(max = 55, message = "destination name must be 55 characters max.") String destination,
                                 @NotEmpty List<String> stations) {

  public TravelLine toTravelLine() {
    return new TravelLine(lineId, startFrom, destination, stations);
  }
}