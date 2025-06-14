package com.travelSafe.buses.travelLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.travelLine.TravelLineRepository;
import com.travelSafe.buses.exceptions.travelLine.TravelLineNotFoundException;
import com.travelSafe.buses.domain.travelLine.model.TravelLine;
import com.travelSafe.buses.domain.travelLine.service.GetTravelLineService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetTravelLineServiceTest {

  @Mock
  private TravelLineRepository travelLineRepository;

  @InjectMocks
  private GetTravelLineService getTravelLineService;

  @Test
  public void gavin_travel_line_exists_when_get_travel_line_called_return_dto() {
    // gavin
    final int travelId = 5;
    final TravelLine travelLine = new TravelLine(travelId, "Cairo", "Asyut",
        List.of("Cairo", "Adly-mansor", "Asyut"));

    // when
    when(travelLineRepository.findById(travelId)).thenReturn(Optional.of(travelLine));
    final TravelLine response = getTravelLineService.execute(travelId);

    // then
    assertEquals(travelLine, response);
    verify(travelLineRepository, times(1)).findById(travelId);
  }

  @Test
  public void gavin_travel_line_does_not_exists_when_get_travel_line_called_throw_not_found() {
    // when
    final int travelId = 10;

    // gavin
    when(travelLineRepository.findById(travelId)).thenReturn(Optional.empty());

    // then
    assertThrows(TravelLineNotFoundException.class, () -> getTravelLineService.execute(travelId));
    verify(travelLineRepository, times(1)).findById(travelId);
  }
}