package com.travelSafe.buses.domain.travelLine.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.domain.travelLine.TravelLineRepository;
import com.travelSafe.buses.domain.travelLine.model.TravelLine;
import com.travelSafe.buses.domain.travelLine.model.dto.InputTravelLineDTO;
import com.travelSafe.buses.exceptions.travelLine.TravelLineNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateTravelLineService implements Command<InputTravelLineDTO, TravelLine> {

  private static final Logger logger = LoggerFactory.getLogger(UpdateTravelLineService.class);
  private final TravelLineRepository travelLineRepository;

  public UpdateTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public TravelLine execute(InputTravelLineDTO input) {
    logger.info("Executing: {} with input:{}", getClass(), input);
    travelLineRepository.findById(input.lineId()).orElseThrow(TravelLineNotFoundException::new);
    return travelLineRepository.save(input.toTravelLine());
  }
}