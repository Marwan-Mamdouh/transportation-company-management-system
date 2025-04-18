package com.travelsave.buses.customer.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDTO {

  private final Integer id;
  private final String fName, lName, phoneNumber;

  public CustomerDTO(@NotNull Customer customer) {
    this.id = customer.getId();
    this.fName = customer.getFirstName();
    this.lName = customer.getLastName();
    this.phoneNumber = customer.getPhoneNumber();
  }
}