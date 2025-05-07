package com.travelSafe.buses.trip.model;

import com.travelSafe.buses.employee.model.Employee;
import com.travelSafe.buses.travelLine.model.TravelLine;
import com.travelSafe.buses.trip.model.DTO.CreateTripDTO;
import com.travelSafe.buses.trip.model.DTO.TripDto;
import com.travelSafe.buses.trip.model.DTO.UpdateTripDTO;
import com.travelSafe.buses.vehicle.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripMapper {

  TripDto toDto(Trip trip);

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