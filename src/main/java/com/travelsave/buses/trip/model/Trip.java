package com.travelsave.buses.trip.model;

import com.travelsave.buses.employee.model.Employee;
import com.travelsave.buses.travelLine.model.TravelLine;
import com.travelsave.buses.vehicle.model.Vehicle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Trip {

  @Id
  @Column(name = "trip_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tripId;

  @OneToOne
  @JoinColumn(name = "car_id", nullable = false)
  private Vehicle car;

  @JoinColumn(name = "line_id")
  @OneToOne(cascade = CascadeType.ALL)
  private TravelLine line;

  @OneToOne
  @JoinColumn(name = "driver_ssn", nullable = false)
  private Employee driver;

  @Column(name = "trip_level", nullable = false)
  private String tripLevel;

  @Positive
  @Column(name = "trip_price", nullable = false)
  private Double price;

  @Column(name = "travel_date", nullable = false)
  private LocalDate travelDate;
  /*
  +-------------+-------------------+------+-----+---------+----------------+
  | Field       | Type              | Null | Key | Default | Extra          |
  +-------------+-------------------+------+-----+---------+----------------+
  | trip_id     | smallint unsigned | NO   | PRI | NULL    | auto_increment |
  | line_id     | tinyint unsigned  | NO   | MUL | NULL    |                |
  | driver_ssn  | char(14)          | NO   | MUL | NULL    |                |
  | car_id      | tinyint unsigned  | NO   | MUL | NULL    |                |
  | trip_level  | varchar(10)       | NO   |     | NULL    |                |
  | trip_price  | smallint unsigned | NO   |     | NULL    |                |
  | travel_date | date              | NO   |     | NULL    |                |
  +-------------+-------------------+------+-----+---------+----------------+
  */
}