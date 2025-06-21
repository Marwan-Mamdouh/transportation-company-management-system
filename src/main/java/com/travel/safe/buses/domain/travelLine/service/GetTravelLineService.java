package com.travel.safe.buses.domain.travelLine.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.exceptions.TravelLineNotFoundException;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
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