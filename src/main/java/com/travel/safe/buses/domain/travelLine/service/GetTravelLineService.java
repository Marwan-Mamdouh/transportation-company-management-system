package com.travel.safe.buses.domain.travelLine.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.travelLine.TravelLineMapper;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.dto.TravelLineResponseDTO;
import com.travel.safe.buses.domain.travelLine.exceptions.TravelLineNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetTravelLineService implements Query<Integer, TravelLineResponseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(GetTravelLineService.class);
  private final TravelLineRepository travelLineRepository;
  private final TravelLineMapper mapper;

  public GetTravelLineService(TravelLineRepository travelLineRepository, TravelLineMapper mapper) {
    this.travelLineRepository = travelLineRepository;
    this.mapper = mapper;
  }

  @Override
  public TravelLineResponseDTO execute(Integer input) {
    logger.debug("Executing: {} with input: {}", getClass(), input);
    final var travelLine = travelLineRepository.findById(input)
        .orElseThrow(TravelLineNotFoundException::new);
    return mapper.toResponse(travelLine);
  }
}