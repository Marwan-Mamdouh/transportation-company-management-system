package com.travelsave.buses.tripPassengers.model;

import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.trip.model.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripPassengersDTO {

  private final Integer tripId;
  private final String employeeFirstname;
  private final String customerFirstname;

  public TripPassengersDTO(Trip trip, Employee employee, Customer customer) {
    this.tripId = trip.getTripId();
    this.employeeFirstname = employee.getFirstName();
    this.customerFirstname = customer.getFirstName();
  }
}