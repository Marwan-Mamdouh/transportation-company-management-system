package com.travelSafe.buses.travelLine.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.travelLine.TravelLineRepository;
import com.travelSafe.buses.travelLine.model.TravelLine;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateTravelLineService implements Command<TravelLine, TravelLine> {

  private final TravelLineRepository travelLineRepository;

  private static final Logger logger = LoggerFactory.getLogger(CreateTravelLineService.class);

  public CreateTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  @Transactional
  public TravelLine execute(TravelLine input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return travelLineRepository.save(input);
  }
}