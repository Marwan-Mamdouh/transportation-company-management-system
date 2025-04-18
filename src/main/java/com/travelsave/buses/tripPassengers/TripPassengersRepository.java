package com.travelsave.buses.tripPassengers;

import com.travelsave.buses.tripPassengers.model.TripPassengerPk;
import com.travelsave.buses.tripPassengers.model.TripPassengers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripPassengersRepository extends JpaRepository<TripPassengers, TripPassengerPk> {

}