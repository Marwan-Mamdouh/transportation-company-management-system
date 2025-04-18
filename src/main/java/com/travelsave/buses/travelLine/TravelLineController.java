package com.travelsave.buses.travelLine;

import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.travelLine.model.TravelLineDTO;
import com.travelsave.buses.travelLine.model.UpdateTravelLineCommand;
import com.travelsave.buses.travelLine.service.CreateTravelLineService;
import com.travelsave.buses.travelLine.service.DeleteTravelLineService;
import com.travelsave.buses.travelLine.service.GetTravelLineService;
import com.travelsave.buses.travelLine.service.GetTravelLinesService;
import com.travelsave.buses.travelLine.service.UpdateTravelLineService;
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
    return getTravelLineService.execute(id);
  }

  @GetMapping("/travel-lines")
  public ResponseEntity<List<TravelLineDTO>> getTravelLines() {
    return getTravelLinesService.execute(null);
  }

  @PutMapping("/travel-line/{id}")
  public ResponseEntity<TravelLineDTO> updateTravelLine(@PathVariable Integer id,
      @RequestBody TravelLine travelLine) {
    return updateTravelLineService.execute(new UpdateTravelLineCommand(id, travelLine));
  }

  @PostMapping("/travel-line")
  public ResponseEntity<TravelLineDTO> createTravelLine(@RequestBody TravelLine travelLine) {
    return createTravelLineService.execute(travelLine);
  }

  @DeleteMapping("/travel-line/{id}")
  public ResponseEntity<Void> deleteTravelLine(@PathVariable Integer id) {
    return deleteTravelLineService.execute(id);
  }
}
