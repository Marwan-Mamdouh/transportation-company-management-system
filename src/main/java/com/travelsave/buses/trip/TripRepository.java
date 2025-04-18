package com.travelsave.buses.trip;

import com.travelsave.buses.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {

}
