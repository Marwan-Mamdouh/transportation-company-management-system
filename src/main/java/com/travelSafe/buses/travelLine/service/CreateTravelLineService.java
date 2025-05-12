package com.travelSafe.buses.travelLine.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.travelLine.TravelLineRepository;
import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.travelLine.model.dto.InputTravelLineDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateTravelLineService implements Command<InputTravelLineDTO, TravelLine> {

  private static final Logger logger = LoggerFactory.getLogger(CreateTravelLineService.class);
  private final TravelLineRepository travelLineRepository;

  public CreateTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  @Transactional
  public TravelLine execute(InputTravelLineDTO input) {
    logger.info("Executing: {} with input: {}", getClass(), input);
    return travelLineRepository.save(input.toTravelLine());
  }
}