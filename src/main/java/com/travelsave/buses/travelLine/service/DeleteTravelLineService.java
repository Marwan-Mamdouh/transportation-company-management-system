package com.travelsave.buses.travelLine.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.travelLine.TravelLineRepository;
import com.travelsave.buses.travelLine.model.TravelLine;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteTravelLineService implements Command<Integer, Void> {

  private final TravelLineRepository travelLineRepository;

  public DeleteTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Integer input) {
    Optional<TravelLine> optionalTravelLine = travelLineRepository.findById(input);
    if (optionalTravelLine.isPresent()) {
      travelLineRepository.deleteById(input);
      return ResponseEntity.noContent().build();
    }
    // todo make exception to be thrown here !!!
    throw new RuntimeException();
  }
}
