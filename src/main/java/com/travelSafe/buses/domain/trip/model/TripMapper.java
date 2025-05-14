package com.travelSafe.buses.domin.trip.model;

import com.travelSafe.buses.domin.employee.model.Employee;
import com.travelSafe.buses.domin.travelLine.model.TravelLine;
import com.travelSafe.buses.domin.trip.model.DTO.CreateTripDTO;
import com.travelSafe.buses.domin.trip.model.DTO.TripResponseDTO;
import com.travelSafe.buses.domin.trip.model.DTO.UpdateTripDTO;
import com.travelSafe.buses.domin.vehicle.model.Vehicle;
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