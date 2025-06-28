package com.travel.safe.buses.domain.travelLine.service;

import com.travel.safe.buses.comman.shared.Query;
import com.travel.safe.buses.domain.travelLine.TravelLineMapper;
import com.travel.safe.buses.domain.travelLine.TravelLineRepository;
import com.travel.safe.buses.domain.travelLine.dto.TravelLineResponseDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetTravelLinesService implements Query<Void, List<TravelLineResponseDTO>> {

  private static final Logger logger = LoggerFactory.getLogger(GetTravelLinesService.class);
  private final TravelLineRepository travelLineRepository;
  private final TravelLineMapper mapper;

  public GetTravelLinesService(TravelLineRepository travelLineRepository, TravelLineMapper mapper) {
    this.travelLineRepository = travelLineRepository;
    this.mapper = mapper;
  }

  @Override
  public List<TravelLineResponseDTO> execute(Void input) {
    logger.debug("Executing: {} with input:{}", getClass(), input);
    final var travelLines = travelLineRepository.findAll();
    return travelLines.stream().map(mapper::toResponse).toList();
  }
}