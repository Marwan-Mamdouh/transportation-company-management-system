package com.travel.safe.buses.domain.travelLine;

import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLineRepository extends JpaRepository<TravelLine, Integer> {

}
