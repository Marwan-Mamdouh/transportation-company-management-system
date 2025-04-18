package com.travelsave.buses.travelLine;

import com.travelsave.buses.travelLine.model.TravelLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLineRepository extends JpaRepository<TravelLine, Integer> {

}
