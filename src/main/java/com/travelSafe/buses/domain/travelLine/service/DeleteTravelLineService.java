package com.travelSafe.buses.domain.travelLine.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domain.travelLine.TravelLineRepository;
import com.travelSafe.buses.domain.travelLine.model.TravelLine;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteTravelLineService implements Command<Integer, Void> {

  private static final Logger logger = LoggerFactory.getLogger(DeleteTravelLineService.class);
  private final TravelLineRepository travelLineRepository;
  private final GetTravelLineService getTravelLineService;

  public DeleteTravelLineService(TravelLineRepository travelLineRepository,
      GetTravelLineService getTravelLineService) {
    this.travelLineRepository = travelLineRepository;
    this.getTravelLineService = getTravelLineService;
  }

  @Override
  @Transactional
  public Void execute(Integer input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    final TravelLine travelLine = getTravelLineService.execute(input);
    travelLineRepository.deleteById(input);
    return null;
  }
}
