package com.travelSafe.buses.travelLine.service;

import com.travelSafe.buses.Query;
import com.travelSafe.buses.travelLine.TravelLineRepository;
import com.travelSafe.buses.travelLine.model.TravelLine;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetTravelLinesService implements Query<Void, List<TravelLine>> {

  private final TravelLineRepository travelLineRepository;

  private static final Logger logger = LoggerFactory.getLogger(GetTravelLinesService.class);

  public GetTravelLinesService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public List<TravelLine> execute(Void input) {
    logger.info("Executing: {} with input:{}", getClass(), input);
    return travelLineRepository.findAll();
  }
}