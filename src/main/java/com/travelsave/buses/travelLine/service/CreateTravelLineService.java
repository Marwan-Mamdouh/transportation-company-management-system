package com.travelsave.buses.travelLine.service;

import com.travelsave.buses.Command;
import com.travelsave.buses.travelLine.TravelLineRepository;
import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.travelLine.model.TravelLineDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateTravelLineService implements Command<TravelLine, TravelLineDTO> {

  private final TravelLineRepository travelLineRepository;

  public CreateTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public ResponseEntity<TravelLineDTO> execute(TravelLine input) {
    TravelLine travelLine = travelLineRepository.save(input);
    return ResponseEntity.status(HttpStatus.CREATED).body(new TravelLineDTO(travelLine));
  }
}