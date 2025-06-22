package com.travel.safe.buses.domain.travelLine.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.dto.InputTravelLineDTO;
import com.travel.safe.buses.domain.travelLine.exceptions.TravelLineNotFoundException;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
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
    logger.debug("Executing: {} with input:{}", getClass(), input);
    travelLineRepository.findById(input.lineId()).orElseThrow(TravelLineNotFoundException::new);
    return travelLineRepository.save(input.toTravelLine());
  }
}