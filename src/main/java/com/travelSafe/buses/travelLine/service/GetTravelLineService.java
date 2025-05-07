package com.travelSafe.buses.travelLine.service;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.exceptions.travelLine.TravelLineNotFoundException;
import com.travelSafe.buses.travelLine.TravelLineRepository;
import com.travelSafe.buses.travelLine.model.TravelLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetTravelLineService implements Query<Integer, TravelLine> {

  private static final Logger logger = LoggerFactory.getLogger(GetTravelLineService.class);
  private final TravelLineRepository travelLineRepository;

  public GetTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public TravelLine execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return travelLineRepository.findById(input).orElseThrow(TravelLineNotFoundException::new);
  }
}