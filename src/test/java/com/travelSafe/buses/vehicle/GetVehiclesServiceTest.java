package com.travelSafe.buses.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.vehicle.model.Vehicle;
import com.travelSafe.buses.vehicle.model.VehicleDTO;
import com.travelSafe.buses.vehicle.service.GetVehiclesService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetVehiclesServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private GetVehiclesService getVehiclesService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

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
    final ResponseEntity<List<VehicleDTO>> response = getVehiclesService.execute(null);
    final List<VehicleDTO> vehicleDTOS = vehicles.stream().map(VehicleDTO::new).toList();

    // then
    assertEquals(ResponseEntity.ok(vehicleDTOS), response);
    verify(vehicleRepository, times(1)).findAll();
  }

  @Test
  public void gavin_vehicles_does_not_exist_when_get_vehicles_service_called_return_empty_list() {
    // gavin

    // when
    when(vehicleRepository.findAll()).thenReturn(List.of());
    final ResponseEntity<List<VehicleDTO>> response = getVehiclesService.execute(null);

    // then
    assertEquals(ResponseEntity.ok(List.of()), response);
    verify(vehicleRepository, times(1)).findAll();
  }
}