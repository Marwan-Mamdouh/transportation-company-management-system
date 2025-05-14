package com.travelSafe.buses.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.domain.vehicle.VehicleRepository;
import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import com.travelSafe.buses.domain.vehicle.service.GetVehiclesService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetVehiclesServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private GetVehiclesService getVehiclesService;

  @Test
  public void gavin_vehicles_exist_when_get_vehicles_service_called_return_dto_list() {
    // gavin
    final Integer vehicle1Id = 15;
    final Vehicle vehicle1 = new Vehicle(vehicle1Id, "hola", 60, "VIP", LocalDate.now(),
        LocalDate.parse("2027-05-09"));
    final Vehicle vehicle2 = new Vehicle(99, "hello", 25, "non", LocalDate.now(),
        LocalDate.parse("2027-02-09"));
    final List<Vehicle> vehicles = List.of(vehicle1, vehicle2);

    // when
    when(vehicleRepository.findAll()).thenReturn(vehicles);
    final List<Vehicle> response = getVehiclesService.execute(null);

    // then
    assertEquals(vehicles, response);
    verify(vehicleRepository, times(1)).findAll();
  }

  @Test
  public void gavin_vehicles_does_not_exist_when_get_vehicles_service_called_return_empty_list() {
    // gavin

    // when
    when(vehicleRepository.findAll()).thenReturn(List.of());
    final List<Vehicle> response = getVehiclesService.execute(null);

    // then
    assertEquals(List.of(), response);
    verify(vehicleRepository, times(1)).findAll();
  }
}