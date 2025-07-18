package com.travel.safe.buses.domain.employee.dto;

import com.travel.safe.buses.comman.validator.PatternLong;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CreateEmployeeDTO(
    @NotNull(message = "ssn can't be null.") @PatternLong(regexp = "^[2-3][0-9]{13}$") Long ssn,
    @NotBlank(message = "First name can't be null or empty.") @Size(max = 55, message = "First name length should be 55 character max.") String firstname,
    @NotBlank(message = "Last name can't be null or empty.") @Size(max = 55, message = "Last name length should be 55 character max.") String lastname,
    @NotBlank(message = "Email can't be null or empty.") @Size(max = 99, message = "Email length should be 99 character max.") @Email String email,
    @NotBlank(message = "Password can't be null or empty.") @Size(min = 8, max = 20, message = "Password length should be between 8 and 20 character.") String password,
    @NotBlank(message = "Phone number can't be null or empty.") @Size(min = 11, max = 11, message = "Phone number length should be 11 character.") @Pattern(regexp = "^01[0-2,5][0-9]{8}$", message = "Not Valid Egyptian phone number.") String phoneNumber,
    @NotNull(message = "Birthday can't be null.") @Past(message = "Birthday can't be in the future.") LocalDate birthday) {

}