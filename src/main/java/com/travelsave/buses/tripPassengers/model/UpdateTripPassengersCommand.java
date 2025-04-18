package com.travelsave.buses.tripPassengers.model;

public record UpdateTripPassengersCommand(TripPassengerPk tripPassengerPk,
                                          TripPassengers tripPassengers) {

}