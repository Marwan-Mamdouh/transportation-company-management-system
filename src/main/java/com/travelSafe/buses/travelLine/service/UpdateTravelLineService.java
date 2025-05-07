package com.travelSafe.buses.travelLine.service;

import com.travelSafe.buses.Command;
import com.travelSafe.buses.exceptions.travelLine.TravelLineNotFoundException;
import com.travelSafe.buses.travelLine.TravelLineRepository;
import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.travelLine.model.dto.UpdateTravelLineDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateTravelLineService implements Command<UpdateTravelLineDTO, TravelLine> {

  private final TravelLineRepository travelLineRepository;

  private static final Logger logger = LoggerFactory.getLogger(UpdateTravelLineService.class);

  public UpdateTravelLineService(TravelLineRepository travelLineRepository) {
    this.travelLineRepository = travelLineRepository;
  }

  @Override
  public TravelLine execute(UpdateTravelLineDTO input) {
    logger.info("Executing: {} with input:{}", getClass(), input);
    final Integer lineId = input.id();
    travelLineRepository.findById(lineId).orElseThrow(TravelLineNotFoundException::new);
    final TravelLine updatedTravelLine = input.updatedTravelLine();
    updatedTravelLine.setLineId(lineId);
    return travelLineRepository.save(updatedTravelLine);
  }
}