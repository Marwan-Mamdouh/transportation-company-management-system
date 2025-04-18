package com.travelsave.buses.travelLine.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.travelLine.TravelLineRepository;
import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.travelLine.model.TravelLineDTO;
import com.travelsave.buses.travelLine.model.UpdateTravelLineCommand;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateTravelLineService implements Command<UpdateTravelLineCommand, TravelLineDTO> {

  private final TravelLineRepository travelLineRepository;

  public UpdateTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public ResponseEntity<TravelLineDTO> execute(UpdateTravelLineCommand input) {
    Optional<TravelLine> optionalTravelLine = travelLineRepository.findById(input.id());
    if (optionalTravelLine.isPresent()) {
      TravelLine updatedTravelLine = travelLineRepository.save(input.updatedTravelLine());
      return ResponseEntity.ok(new TravelLineDTO(updatedTravelLine));
    }
    // todo complete thrown this exception
    throw new RuntimeException();
  }
}