package com.travel.safe.buses.domain.vehicle;

import com.travel.safe.buses.domain.vehicle.dto.CreateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.dto.UpdateVehicleDTO;
import com.travel.safe.buses.domain.vehicle.dto.VehicleResponseDTO;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMapper {


  VehicleResponseDTO toResponseDto(Vehicle vehicle);
//  public VehicleResponseDTO(Vehicle vehicle) {
//    this.vehicleId = vehicle.getVehicleId();
//    this.capacity = vehicle.getCapacity();
//    this.vehicleLevel = vehicle.getVehicleLevel();
//    this.plateNumber = vehicle.getPlateNumber();
//  }

  Vehicle toEntity(UpdateVehicleDTO vehicleDto);

  @Mapping(target = "vehicleId", ignore = true)
  Vehicle toEntity(CreateVehicleDTO vehicleDto);
}