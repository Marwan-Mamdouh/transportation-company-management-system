package com.travel.safe.buses.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travel.safe.buses.domain.vehicle.VehicleMapper;
import com.travel.safe.buses.domain.vehicle.VehicleRepository;
import com.travel.safe.buses.domain.vehicle.exceptions.VehicleNotFoundException;
import com.travel.safe.buses.domain.vehicle.model.Vehicle;
import com.travel.safe.buses.domain.vehicle.service.GetVehicleService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetVehicleServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @Spy
  private VehicleMapper mapper;

  @InjectMocks
  private GetVehicleService getVehicleService;

  @Test
  void givenVehicleExist_whenGetVehicleService_returnVehicle() {
    // given
    final Integer vehicleId = 15;
    final Vehicle vehicle = new Vehicle(vehicleId, "hola", 60, "VIP", LocalDate.now(),
        LocalDate.parse("2027-05-09"));
    // when
    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
    final var response = getVehicleService.execute(vehicleId);
    // then
    assertEquals(mapper.toResponseDto(vehicle), response);
    verify(vehicleRepository, times(1)).findById(vehicleId);
  }

  @Test
  void givenVehicleDoesNotExist_whenGetVehicleService_throwVehicleNotFoundException() {
    // given
    final Integer vehicleId = 90;

    // when
    when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

    // then
    assertThrows(VehicleNotFoundException.class, () -> getVehicleService.execute(vehicleId));
    verify(vehicleRepository, times(1)).findById(vehicleId);
  }
}