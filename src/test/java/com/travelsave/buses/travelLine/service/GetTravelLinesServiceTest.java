package com.travelsave.buses.travelLine.service;

import com.travelsave.buses.trip.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GetTravelLinesServiceTest {

  @Mock
  private TripRepository tripRepository;

  @InjectMocks
  private GetTravelLinesService getTravelLinesService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin() {

  }

}