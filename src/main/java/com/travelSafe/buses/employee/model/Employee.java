package com.travelSafe.buses.employee.model;

import com.travelSafe.buses.department.model.Department;
import com.travelSafe.buses.validator.PatternLong;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Employee {

  @Id
  @NotNull
  @Max(39999999999999L)
  @PatternLong(regexp = "^[2-3][0-9]{13}$")
  private Long ssn;

  @Column(nullable = false)
  @NotBlank(message = "First name can't be null or empty.")
  @Size(max = 55, message = "First name length should be 55 character max.")
  private String firstName;

  @Column(nullable = false)
  @NotBlank(message = "Last name can't be null or empty.")
  @Size(max = 55, message = "Last name length should be 55 character max.")
  private String lastName;

  @Email(message = "Not valid Email.")
  @Column(unique = true, nullable = false)
  @NotBlank(message = "Email can't be null or empty.")
  @Size(max = 99, message = "Email length should be 99 character max.")
  private String email;

  @Column(unique = true, nullable = false)
  @NotBlank(message = "Phone number can't be null or empty.")
  @Size(max = 11, message = "Phone number length should be 11 character max.")
  @Pattern(regexp = "^01[0-2,5][0-9]{8}$", message = "Not Valid Egyptian phone number.")
  private String phoneNumber;

  @Transient
  private Integer age;

  @Column(name = "birth_day", nullable = false)
  @NotNull(message = "birthday can't be null.")
  @Past(message = "birthday can't be in the future.")
  private LocalDate birthday;

//  @NotBlank
//  @Column(nullable = false)
//  private String hashedPassword;

  // Self-referencing relationship: one supervisor can supervise many employees
  @JoinColumn(name = "supervisor")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Employee supervisor;

  @JoinColumn(name = "department_id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Department department;

  @Max(100)
  @Positive
  @Transient // ensures JPA doesn't try to persist this value
  public Integer getAge() {
    if (birthday == null) {
      return null;
    }
    return Period.between(birthday, LocalDate.now()).getYears();
  }
}