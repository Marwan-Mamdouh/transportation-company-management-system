package com.travelsave.buses.travelLine.service;

import com.travelsave.buses.Query;
import com.travelsave.buses.travelLine.TravelLineRepository;
import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.travelLine.model.TravelLineDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetTravelLinesService implements Query<Void, List<TravelLineDTO>> {

  private final TravelLineRepository travelLineRepository;

  public GetTravelLinesService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public ResponseEntity<List<TravelLineDTO>> execute(Void input) {
    List<TravelLine> travelLines = travelLineRepository.findAll();
    List<TravelLineDTO> travelLineDTOS = travelLines.stream().map(TravelLineDTO::new).toList();
    return ResponseEntity.ok(travelLineDTOS);
  }
}