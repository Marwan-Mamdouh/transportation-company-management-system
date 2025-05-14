package com.travelSafe.buses.domain.vehicle;

import com.travelSafe.buses.domain.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

  @Query("select (count(v) > 0) from Vehicle v where upper(v.plateNumber) = upper(:plateNumber)")
  boolean existsByPlateNumberIgnoreCase(@Param("plateNumber") @NonNull String plateNumber);

}