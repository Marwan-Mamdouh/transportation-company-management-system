package com.travelSafe.buses.domain.travelLine.service;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.domain.travelLine.TravelLineRepository;
import com.travelSafe.buses.domain.travelLine.model.TravelLine;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetTravelLinesService implements Query<Void, List<TravelLine>> {

  private static final Logger logger = LoggerFactory.getLogger(GetTravelLinesService.class);
  private final TravelLineRepository travelLineRepository;

  public GetTravelLinesService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public List<TravelLine> execute(Void input) {
    logger.info("Executing: {} with input:{}", getClass(), input);
    return travelLineRepository.findAll();
  }
}