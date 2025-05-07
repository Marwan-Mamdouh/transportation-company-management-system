package com.travelSafe.buses.travelLine;

import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.travelLine.model.TravelLineDTO;
import com.travelSafe.buses.travelLine.model.UpdateTravelLineCommand;
import com.travelSafe.buses.travelLine.service.CreateTravelLineService;
import com.travelSafe.buses.travelLine.service.DeleteTravelLineService;
import com.travelSafe.buses.travelLine.service.GetTravelLineService;
import com.travelSafe.buses.travelLine.service.GetTravelLinesService;
import com.travelSafe.buses.travelLine.service.UpdateTravelLineService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

  @GetMapping("/travel-line/{id}")
  public ResponseEntity<TravelLineDTO> getTravelLine(@PathVariable Integer id) {
    final TravelLine travelLine = getTravelLineService.execute(id);
    return ResponseEntity.ok(new TravelLineDTO(travelLine));
  }

  @GetMapping("/travel-lines")
  public ResponseEntity<List<TravelLineDTO>> getTravelLines() {
    final List<TravelLine> travelLines = getTravelLinesService.execute(null);
    return ResponseEntity.ok(travelLines.stream().map(TravelLineDTO::new).toList());
  }

  @PutMapping("/travel-line/{id}")
  public ResponseEntity<TravelLineDTO> updateTravelLine(@PathVariable Integer id,
      @RequestBody TravelLine travelLine) {
    final TravelLine savedTravelLine = updateTravelLineService.execute(
        new UpdateTravelLineCommand(id, travelLine));
    return ResponseEntity.ok(new TravelLineDTO(savedTravelLine));
  }

  @PostMapping("/travel-line")
  public ResponseEntity<TravelLineDTO> createTravelLine(@RequestBody TravelLine travelLine) {
    final TravelLine savedTravelLine = createTravelLineService.execute(travelLine);
    return ResponseEntity.ok(new TravelLineDTO(savedTravelLine));
  }

  @DeleteMapping("/travel-line/{id}")
  public ResponseEntity<Void> deleteTravelLine(@PathVariable Integer id) {
    return ResponseEntity.ok(deleteTravelLineService.execute(id));
  }
}