package com.travelsave.buses.customer.model;

import com.travelsave.buses.model.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {

  @Id
  @Positive
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  public Customer(Integer id, String firstName, String lastName, String email, String phoneNumber,
      LocalDate date) {
    super(firstName, lastName, email, phoneNumber, date);
    this.id = id;
  }
}