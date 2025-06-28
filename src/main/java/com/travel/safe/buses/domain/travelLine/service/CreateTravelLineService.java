package com.travel.safe.buses.domain.travelLine.service;

import com.travel.safe.buses.comman.shared.Command;
import com.travel.safe.buses.domain.travelLine.TravelLineMapper;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.dto.InputTravelLineDTO;
import com.travel.safe.buses.domain.travelLine.dto.TravelLineResponseDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateTravelLineService implements Command<InputTravelLineDTO, TravelLineResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(CreateTravelLineService.class);
  private final TravelLineRepository travelLineRepository;
  private final TravelLineMapper mapper;

  public CreateTravelLineService(TravelLineRepository travelLineRepository,
      TravelLineMapper mapper) {
    this.travelLineRepository = travelLineRepository;
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public TravelLineResponseDTO execute(InputTravelLineDTO input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var savedTravelLine = travelLineRepository.save(mapper.toTravelLine(input));
    return mapper.toResponse(savedTravelLine);
  }
}