package com.travel.safe.buses.domain.employee.dto;

public record EmployeeResponseDTO(Long ssn, String firstname, String lastname, String email,
                                  String phoneNumber) implements DtoResponseI {

}