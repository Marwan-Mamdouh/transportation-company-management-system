package com.travelsave.buses.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

  @Column(name = "first_name", nullable = false)
  @NotBlank(message = "First name can't be null or empty.")
  @Size(max = 55, message = "First name length should be 55 character max.")
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @NotBlank(message = "Last name can't be null or empty.")
  @Size(max = 55, message = "Last name length should be 55 character max.")
  private String lastName;

  @Email(message = "Not valid Email.")
  @Column(unique = true, nullable = false)
  @NotNull(message = "Email can't be null.")
  private String email;

  @NotBlank(message = "Phone number can't be null or empty.")
  @Column(name = "phone_number", unique = true, nullable = false)
  @Pattern(regexp = "^01[0-2,5][0-9]{8}$", message = "Phone number must start with 01 then one of [0, 1, 2, 5] then 8 digits.")
  private String phoneNumber;

  @Column(name = "birth_day", nullable = false)
  @NotNull(message = "birthday can't be null.")
  @Past(message = "birthday can't be in the future.")
  private LocalDate birthday;

  @Positive
  @Transient // ensures JPA doesn't try to persist this value
  public Integer getAge() {
    if (birthday == null) {
      return null;
    }
    return Period.between(birthday, LocalDate.now()).getYears();
  }
}