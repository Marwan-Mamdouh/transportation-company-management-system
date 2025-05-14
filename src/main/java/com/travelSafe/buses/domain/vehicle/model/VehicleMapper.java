package com.travelSafe.buses.domin.vehicle.model;

import com.travelSafe.buses.domin.vehicle.model.dto.CreateVehicleDTO;
import com.travelSafe.buses.domin.vehicle.model.dto.UpdateVehicleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

  Vehicle fromUpdateDtoToEntity(UpdateVehicleDTO vehicleDto);

  Vehicle fromCreateDtoToEntity(CreateVehicleDTO vehicleDto);
}