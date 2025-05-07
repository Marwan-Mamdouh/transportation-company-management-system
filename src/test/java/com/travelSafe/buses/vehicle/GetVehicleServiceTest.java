package com.travelSafe.buses.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelSafe.buses.exceptions.vehicle.VehicleNotFoundException;
import com.travelSafe.buses.vehicle.model.Vehicle;
import com.travelSafe.buses.vehicle.model.VehicleDTO;
import com.travelSafe.buses.vehicle.service.GetVehicleService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetVehicleServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private GetVehicleService getVehicleService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_vehicle_exist_when_get_vehicle_service_called_return_dto() {
    final Integer vehicleId = 15;
    final Vehicle vehicle = new Vehicle(vehicleId, "hola", 60, "VIP", LocalDate.now(),
        LocalDate.parse("2027-05-09"));

    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
    final ResponseEntity<VehicleDTO> response = getVehicleService.execute(vehicleId);

    assertEquals(ResponseEntity.ok(new VehicleDTO(vehicle)), response);
    verify(vehicleRepository, times(1)).findById(vehicleId);
  }

  @Test
  public void gavin_vehicle_does_not_exist_when_get_vehicle_service_called_throw_not_found() {
    // gavin
    final Integer vehicleId = 90;

    // when
    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

    // then
    assertThrows(VehicleNotFoundException.class, () -> getVehicleService.execute(vehicleId));
    verify(vehicleRepository, times(1)).findById(vehicleId);
  }
}