package com.travelsave.buses.travelLine.service;

import com.travelsave.buses.Query;
import com.travelsave.buses.exceptions.travelLine.TravelLineNotFoundException;
import com.travelsave.buses.travelLine.TravelLineRepository;
import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.travelLine.model.TravelLineDTO;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetTravelLineService implements Query<Integer, TravelLineDTO> {

  private final TravelLineRepository travelLineRepository;

  public GetTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public ResponseEntity<TravelLineDTO> execute(Integer input) {
    Optional<TravelLine> optionalTravelLine = travelLineRepository.findById(input);
    if (optionalTravelLine.isPresent()) {
      return ResponseEntity.ok(new TravelLineDTO(optionalTravelLine.get()));
    }
    // todo make exception to be thrown here
    throw new TravelLineNotFoundException();
  }
}