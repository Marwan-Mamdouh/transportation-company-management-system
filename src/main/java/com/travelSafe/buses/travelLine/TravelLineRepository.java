package com.travelSafe.buses.travelLine;

import com.travelSafe.buses.travelLine.model.TravelLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLineRepository extends JpaRepository<TravelLine, Integer> {

}
