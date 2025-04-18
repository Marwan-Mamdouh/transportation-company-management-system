package com.travelsave.buses.employee.model;

public record UpdateEmployeeCommand(String id, Employee updateedEmployee) {

}