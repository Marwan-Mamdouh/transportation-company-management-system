package com.travel.safe.buses.domain.trip;

import com.travel.safe.buses.domain.employee.model.Employee;
import com.travel.safe.buses.domain.travelLine.model.TravelLine;
import com.travel.safe.buses.domain.trip.DTO.CreateTripDTO;
import com.travel.safe.buses.domain.trip.DTO.TripResponseDTO;
import com.travel.safe.buses.domain.trip.DTO.UpdateTripDTO;
import com.travel.safe.buses.domain.trip.model.Trip;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripMapper {

  TripResponseDTO toDto(Trip trip);

  Trip toEntity(CreateTripDTO tripDTO);

  Trip toEntity(UpdateTripDTO tripDTO);

//  @Mapping(target = "driver", source = "employeeId")
//  @Mapping(target = "car", source = "vehicleId")
//  @Mapping(target = "travelLine", source = "travelLine")
//  Trip toEntity(TripDto dto);

  default Employee map(Long employeeId) {
    if (employeeId == null) {
      return null;
    }
    Employee emp = new Employee();
    emp.setSsn(employeeId);
    return emp;
  }

  default Vehicle map(Integer vehicleId) {
    if (vehicleId == null) {
      return null;
    }
    Vehicle car = new Vehicle();
    car.setVehicleId(vehicleId);
    return car;
  }

  default TravelLine mapLine(Integer lineId) {
    if (lineId == null) {
      return null;
    }
    TravelLine line = new TravelLine();
    line.setLineId(lineId);
    return line;
  }
}