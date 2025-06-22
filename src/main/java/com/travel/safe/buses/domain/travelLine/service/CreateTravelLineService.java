package com.travel.safe.buses.domain.travelLine.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.dto.InputTravelLineDTO;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
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
    logger.debug("Executing: {} with input: {}", getClass(), input);
    return travelLineRepository.save(input.toTravelLine());
  }
}