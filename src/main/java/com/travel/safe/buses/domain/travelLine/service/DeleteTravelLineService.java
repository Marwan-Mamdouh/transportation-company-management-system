package com.travel.safe.buses.domain.travelLine.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
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
    logger.debug("Executing: {} with input: {}", getClass(), input);
    getTravelLineService.execute(input);
    travelLineRepository.deleteById(input);
    return null;
  }
}
