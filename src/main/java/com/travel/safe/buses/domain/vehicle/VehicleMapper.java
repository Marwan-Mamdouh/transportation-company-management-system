package com.travel.safe.buses.domain.vehicle;

import com.travel.safe.buses.domain.vehicle.dto.CreateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.dto.UpdateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

  Vehicle fromUpdateDtoToEntity(UpdateVehicleDTO vehicleDto);

  Vehicle fromCreateDtoToEntity(CreateVehicleDTO vehicleDto);
}