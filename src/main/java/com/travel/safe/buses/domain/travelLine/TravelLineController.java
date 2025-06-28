package com.travel.safe.buses.domain.travelLine;

import com.travel.safe.buses.domain.travelLine.dto.InputTravelLineDTO;
import com.travel.safe.buses.domain.travelLine.dto.TravelLineResponseDTO;
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
@RequestMapping("/api/travel-lines")
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
    return ResponseEntity.ok(getTravelLineService.execute(id));
  }

  @GetMapping("/all")
  public ResponseEntity<List<TravelLineResponseDTO>> getTravelLines() {
    return ResponseEntity.ok(getTravelLinesService.execute(null));
  }

  @PutMapping
  public ResponseEntity<TravelLineResponseDTO> updateTravelLine(
      @Valid @RequestBody InputTravelLineDTO dto) {
    return ResponseEntity.ok(updateTravelLineService.execute(dto));
  }

  @PostMapping
  public ResponseEntity<TravelLineResponseDTO> createTravelLine(
      @Valid @RequestBody InputTravelLineDTO dto) {
    return ResponseEntity.ok(createTravelLineService.execute(dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTravelLine(@PathVariable Integer id) {
    return ResponseEntity.ok(deleteTravelLineService.execute(id));
  }
}