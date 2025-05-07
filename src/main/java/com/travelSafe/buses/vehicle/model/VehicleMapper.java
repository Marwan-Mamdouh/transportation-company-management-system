package com.travelSafe.buses.vehicle.model;

import com.travelSafe.buses.vehicle.model.dto.CreateVehicleDto;
import com.travelSafe.buses.vehicle.model.dto.UpdateVehicleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

  Vehicle fromUpdateDtoToEntity(UpdateVehicleDto vehicleDto);

  Vehicle fromCreateDtoToEntity(CreateVehicleDto vehicleDto);
}