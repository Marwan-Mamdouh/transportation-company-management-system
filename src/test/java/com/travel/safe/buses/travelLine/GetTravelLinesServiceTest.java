package com.travel.safe.buses.travelLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import com.travel.safe.buses.domain.travelLine.service.GetTravelLinesService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetTravelLinesServiceTest {

  @Mock
  private TravelLineRepository travelLineRepository;

  @InjectMocks
  private GetTravelLinesService getTravelLinesService;

  @Test
  void givenTravelLineExist_whenGetTravelLinesCalled_returnTravelLineList() {
    // given
    final TravelLine travelLine1 = new TravelLine(19, "asyut", "cairo", new ArrayList<>());
    final TravelLine travelLine2 = new TravelLine(91, "cairo", "asyut", new ArrayList<>());
    final List<TravelLine> travelLines = List.of(travelLine1, travelLine2);

    // when
    when(travelLineRepository.findAll()).thenReturn(travelLines);
    final List<TravelLine> response = getTravelLinesService.execute(null);

    // then
    assertEquals(travelLines, response);
    verify(travelLineRepository, times(1)).findAll();
  }

  @Test
  void givenTravelLineDoseNotExist_whenGetTravelLinesCalled_returnEmptyList() {
    // given & when
    when(travelLineRepository.findAll()).thenReturn(List.of());
    final List<TravelLine> response = getTravelLinesService.execute(null);

    // then
    assertEquals(List.of(), response);
    verify(travelLineRepository, times(1)).findAll();
  }
}