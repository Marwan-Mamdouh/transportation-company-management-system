package com.travelSafe.buses.travelLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.travelLine.model.TravelLineDTO;
import com.travelSafe.buses.travelLine.service.GetTravelLinesService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetTravelLinesServiceTest {

  @Mock
  private TravelLineRepository travelLineRepository;

  @InjectMocks
  private GetTravelLinesService getTravelLinesService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_travel_line_exist_when_get_travel_lines_called_return_dto_list() {
    // gavin
    final TravelLine travelLine1 = new TravelLine(19, "asyut", "cairo", new ArrayList<>());
    final TravelLine travelLine2 = new TravelLine(91, "cairo", "asyut", new ArrayList<>());
    final List<TravelLine> travelLines = List.of(travelLine1, travelLine2);

    // when
    when(travelLineRepository.findAll()).thenReturn(travelLines);
    final ResponseEntity<List<TravelLineDTO>> response = getTravelLinesService.execute(null);
    final List<TravelLineDTO> travelLineDTOS = travelLines.stream().map(TravelLineDTO::new)
        .toList();

    // then
    assertEquals(ResponseEntity.ok(travelLineDTOS), response);
    verify(travelLineRepository, times(1)).findAll();
  }

  @Test
  public void gavin_travel_line_does_not_exist_when_get_travel_lines_called_return_empty_list() {
    // gavin & when
    when(travelLineRepository.findAll()).thenReturn(List.of());
    final ResponseEntity<List<TravelLineDTO>> response = getTravelLinesService.execute(null);

    // then
    assertEquals(ResponseEntity.ok(List.of()), response);
    verify(travelLineRepository, times(1)).findAll();
  }
}