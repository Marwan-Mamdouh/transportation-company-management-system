package com.travelSafe.buses.domain.vehicle;

import com.travelSafe.buses.domain.vehicle.dto.CreateVehicleDTO;
import com.travelSafe.buses.domain.vehicle.dto.UpdateVehicleDTO;
import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

  Vehicle fromUpdateDtoToEntity(UpdateVehicleDTO vehicleDto);

  Vehicle fromCreateDtoToEntity(CreateVehicleDTO vehicleDto);
}