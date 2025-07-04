package com.travel.safe.buses.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.vehicle.VehicleMapper;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import com.travel.safe.buses.domain.vehicle.service.GetVehiclesService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetVehiclesServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @Spy
  private VehicleMapper mapper;

  @InjectMocks
  private GetVehiclesService getVehiclesService;

  @Test
  void givenVehiclesExist_whenGetVehiclesService_returnVehiclesList() {
    // given
    final Integer vehicle1Id = 15;
    final Vehicle vehicle1 = new Vehicle(vehicle1Id, "hola", 60, "VIP", LocalDate.now(),
        LocalDate.parse("2027-05-09"));
    final Vehicle vehicle2 = new Vehicle(99, "hello", 25, "non", LocalDate.now(),
        LocalDate.parse("2027-02-09"));
    final List<Vehicle> vehicles = List.of(vehicle1, vehicle2);
    final var vehicleDto = vehicles.stream().map(mapper::toResponseDto).toList();

    // when
    when(vehicleRepository.findAll()).thenReturn(vehicles);
    final var response = getVehiclesService.execute(null);

    // then
    assertEquals(vehicleDto, response);
    verify(vehicleRepository, times(1)).findAll();
  }

  @Test
  void givenVehiclesDoesNotExist_whenGetVehiclesService_returnEmptyList() {
    // given

    // when
    when(vehicleRepository.findAll()).thenReturn(List.of());
    final var response = getVehiclesService.execute(null);

    // then
    assertEquals(List.of(), response);
    verify(vehicleRepository, times(1)).findAll();
  }
}