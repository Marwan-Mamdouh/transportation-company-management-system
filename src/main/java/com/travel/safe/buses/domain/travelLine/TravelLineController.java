package com.travel.safe.buses.domain.travelLine;

import com.travel.safe.buses.domain.travelLine.dto.InputTravelLineDTO;
import com.travel.safe.buses.domain.travelLine.dto.TravelLineResponseDTO;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import com.travel.safe.buses.domain.travelLine.service.CreateTravelLineService;
import com.travel.safe.buses.domain.travelLine.service.DeleteTravelLineService;
import com.travel.safe.buses.domain.travelLine.service.GetTravelLineService;
import com.travel.safe.buses.domain.travelLine.service.GetTravelLinesService;
import com.travel.safe.buses.domain.travelLine.service.UpdateTravelLineService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travel-line")
public class TravelLineController {

  private final CreateTravelLineService createTravelLineService;

  private final DeleteTravelLineService deleteTravelLineService;

  private final GetTravelLineService getTravelLineService;
  private final GetTravelLinesService getTravelLinesService;

  private final UpdateTravelLineService updateTravelLineService;

  public TravelLineController(CreateTravelLineService createTravelLineService,
      DeleteTravelLineService deleteTravelLineService, GetTravelLineService getTravelLineService,
      GetTravelLinesService getTravelLinesService,
      UpdateTravelLineService updateTravelLineService) {
    this.createTravelLineService = createTravelLineService;
    this.deleteTravelLineService = deleteTravelLineService;
    this.getTravelLineService = getTravelLineService;
    this.getTravelLinesService = getTravelLinesService;
    this.updateTravelLineService = updateTravelLineService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TravelLineResponseDTO> getTravelLine(@PathVariable Integer id) {
    final TravelLine travelLine = getTravelLineService.execute(id);
    return ResponseEntity.ok(new TravelLineResponseDTO(travelLine));
  }

  @GetMapping("/all")
  public ResponseEntity<List<TravelLineResponseDTO>> getTravelLines() {
    final List<TravelLine> travelLines = getTravelLinesService.execute(null);
    return ResponseEntity.ok(travelLines.stream().map(TravelLineResponseDTO::new).toList());
  }

  @PutMapping
  public ResponseEntity<TravelLineResponseDTO> updateTravelLine(
      @Valid @RequestBody InputTravelLineDTO dto) {
    final TravelLine savedTravelLine = updateTravelLineService.execute(dto);
    return ResponseEntity.ok(new TravelLineResponseDTO(savedTravelLine));
  }

  @PostMapping
  public ResponseEntity<TravelLineResponseDTO> createTravelLine(
      @Valid @RequestBody InputTravelLineDTO dto) {
    final TravelLine savedTravelLine = createTravelLineService.execute(dto);
    return ResponseEntity.ok(new TravelLineResponseDTO(savedTravelLine));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTravelLine(@PathVariable Integer id) {
    return ResponseEntity.ok(deleteTravelLineService.execute(id));
  }
}