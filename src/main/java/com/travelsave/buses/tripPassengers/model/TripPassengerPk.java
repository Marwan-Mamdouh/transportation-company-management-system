package com.travelsave.buses.tripPassengers.model;

import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.trip.model.Trip;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public final class TripPassengerPk {

  @ManyToOne
  @JoinColumn(name = "trip_id", nullable = false)
  private Trip tripId;

  @ManyToOne
  @JoinColumn(name = "employee_ssn")
  private Employee employeeSsn;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customerId;
}