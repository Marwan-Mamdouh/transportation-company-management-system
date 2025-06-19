# Transportation management system

---

A simple back end api project using spring boot, spring data jpa, spring security and
MySQL for a database built for learning purposes,it's under development right now.
You can see it in more structure way with this
tool [![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/Marwan-Mamdouh/transportation-company-management-system)

## Features

---

CRUD operations for employees, departments, vehicles, trips, and seat booking.

## Requirements

---

- **Java 21** or higher
- **Maven 3.6+** (if building from source)

## Quick Start

---

### Option 1: Using Pre-built JAR

1. Download the latest JAR file from the repository
2. Run the application:
   ```bash
   java -jar buses-0.1-SNAPSHOT.jar
   ```

### Option 2: Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/Marwan-Mamdouh/transportation-company-management-system
   cd transportation-company-management-system
   ```

2. Build the project:
   ```bash
   ./mvnw clean package
   ```

3. Run the application:
   ```bash
   java -jar target/buses-0.1-SNAPSHOT/.jar
   ```

## Usage

---

The application runs as a web-server.
this part is under development right now and all we have is Swagger-ui

[//]: # (Use the following end-points &#40;APIs&#41; with their arguments:)

## Data Storage

---

- Expenses are stored in `MySQL` database living on a docker container in your home directory
- The Schema will be created automatically when you run the app on your machine for the first time

## Development

---

### Project Structure

```
src/
├── main/java/com/marwan/travelSafe/buses
│   ├── configuration/                      # Application configuration
│   │    ├── cache/
│   │    │     └── CacheConfiguration.java # cache config
│   │    └── security/
│   │          ├── BCryptPasswordConfig    # password encoder config
│   │          ├── JwtConfig               # Jwt encoder and decoder
│   │          └── SecurityConfig          # spring security config
│   ├── domain/
│   │    ├── department/
│   │    │     ├── /controller               # department controller package
│   │    │     │     ├── DepartmentController.java
│   │    │     │     └── DepartmentsController.java
│   │    │     ├── /model                    # department controller package
│   │    │     │     ├── /DTO
│   │    │     │     │     ├── CreateDepartmentDto.java
│   │    │     │     │     ├── DepartmentResponseDTO.java
│   │    │     │     │     ├── PromteManagerDto.java
│   │    │     │     │     └── UpdateDepartmentDTO.java
│   │    │     │     ├── Department.java
│   │    │     │     └── DepartmentMapper.java
│   │    │     ├── /service                  # department controller package
│   │    │     │     ├── CreateDepartmentService.java
│   │    │     │     ├── DeleteDepartmentService.java
│   │    │     │     ├── GetDepartmentService.java
│   │    │     │     ├── GetDepartmentsService.java
│   │    │     │     ├── PromoteManagerService.java
│   │    │     │     └── UpdateDepartmentService.java
│   │    │     └── DepartmentRepository.java # department controller package
│   │    ├── employee/
│   │    │     ├── /controller               # employee controller package
│   │    │     │     ├── EmployeeAuthenticationController.java
│   │    │     │     ├── EmployeeController.java
│   │    │     │     └── EmployeesController.java
│   │    │     ├── /model                    # department controller package
│   │    │     │     ├── /dto
│   │    │     │     │     ├── EmployeeLoginDTO.java
│   │    │     │     │     ├── EmployeePayCheckDTO.java
│   │    │     │     │     ├── EmployeeResponseDTO.java
│   │    │     │     │     └── InputEmployeeDTO.java
│   │    │     │     ├── /enums
│   │    │     │     │     └── Role.java
│   │    │     │     ├── /projection
│   │    │     │     │     └── EmployeeAuth.java
│   │    │     │     ├── Emoloyee.java
│   │    │     │     └── EmployeeMapper.java
│   │    │     ├── /service                  # department controller package
│   │    │     │     ├── /get
│   │    │     │     │     ├── CountEmployeesService.java
│   │    │     │     │     ├── GetEmployeesByDepartmentIdService.java
│   │    │     │     │     ├── GetEmployeesBySupervisorService.java
│   │    │     │     │     ├── GetEmployeeService.java
│   │    │     │     │     └── GetEmployeesService.java
│   │    │     │     ├── CreateEmployeeService.java
│   │    │     │     ├── DeleteEmployeeService.java
│   │    │     │     ├── EmployeeLoginService.java
│   │    │     │     └── UpdateEmployeeServicee.java
│   │    │     └── EmployeeRepository.java # department controller package
│   │    ├── /seats
│   │    │     ├── /model                    # department controller package
│   │    │     │     ├── /dto
│   │    │     │     │     ├── BookSeatDTO.java
│   │    │     │     │     ├── SeatResponseDTO.java
│   │    │     │     │     └── UpdateTripSeatDTO.java
│   │    │     │     ├── Seat.java
│   │    │     │     └── SeatId.java
│   │    │     ├── /service                  # department controller package
│   │    │     │     ├── BookSeatService.java
│   │    │     │     └── SearchForSeatsByTripIdService.java
│   │    │     ├── SeatController.java
│   │    │     └── SeatRepository.java # department controller package
│   │    ├── /travelLine
│   │    │     ├── /model                    # department controller package
│   │    │     │     ├── /dto
│   │    │     │     │    ├── InputTravelLineDTO.java
│   │    │     │     │    └── TravelLineResponseDTO.java
│   │    │     │     └── TravelLine.java
│   │    │     ├── /service                  # department controller package
│   │    │     │     ├── CreateTravelLineService.java
│   │    │     │     ├── DeleteTravelLineService.java
│   │    │     │     ├── GetTravelLineService.java
│   │    │     │     ├── GetTravelLinesService.java
│   │    │     │     └── UpdateTravelLineService.java
│   │    │     ├── TravelLineController.java
│   │    │     └── TravelLineRepository.java # department controller package
│   │    ├── /trip
│   │    │     ├── /model                    # department controller package
│   │    │     │     ├── /dto
│   │    │     │     │     ├── CreateTripDTO.java
│   │    │     │     │     ├── TripAvailabilityDTO.java
│   │    │     │     │     ├── TripResponseDTO.java
│   │    │     │     │     ├── TripSearchDTO.java
│   │    │     │     │     └── UpdateTripDTO.java
│   │    │     │     ├── /projection
│   │    │     │     │     └── TripAvailabilityProjection.java
│   │    │     │     ├── Trip.java
│   │    │     │     └── TripMapper.java
│   │    │     ├── /service                  # department controller package
│   │    │     │     ├── CreateTripService.java
│   │    │     │     ├── DeleteTripService.java
│   │    │     │     ├── GetTripService.java
│   │    │     │     ├── SearchForTripsService.java
│   │    │     │     └── UpdateTripService.java
│   │    │     ├── TripController.java
│   │    │     └── TripRepository.java # department controller package
│   │    └── /vehicle
│   │          ├── /model                    # department controller package
│   │          │     ├── /dto
│   │          │     │     ├── CreateVehicleDTO.java
│   │          │     │     ├── UpdateVehicleDTO.java
│   │          │     │     └── VehicleResponseDTO.java
│   │          │     ├── Vehicle.java
│   │          │     └── VehicleMapper.java
│   │          ├── /service                  # department controller package
│   │          │     ├── CreateVehicleService.java
│   │          │     ├── DeleteVehicleService.java
│   │          │     ├── GetVehicleService.java
│   │          │     ├── GetVehiclesleService.java
│   │          │     └── UpdateVehicleService.java
│   │          ├── VehicleController.java
│   │          └── VehicleRepository.java # department controller package
│   ├── /exceptions
│   │    ├── /department
│   │    │     └── DepartmentNotFoundException.java
│   │    ├── /employee
│   │    │     ├── DuplicateEmployeeEmailException.java
│   │    │     ├── DuplicateEmployeeIdException.java
│   │    │     ├── DuplicateEmployeePhoneNumberException.java
│   │    │     ├── EmailNotFoundException.java
│   │    │     ├── EmployeeNotFoundException.java
│   │    │     ├── NotValidPasswordException.java
│   │    │     └── SupervisorNotFoundException.java
│   │    ├── /seat
│   │    │     ├── SeatAlreadyBookedException.java
│   │    │     └── SeatNotFoundException.java
│   │    ├── /travelLine
│   │    │     ├── TravelLineNotFoundException.java
│   │    │     └── TravelLineNotValidException.java
│   │    ├── /trip
│   │    │     ├── TripNotFoundException.java
│   │    │     └── TripNotValidException.java
│   │    ├── /vehicle
│   │    │     ├── DuplicateVehiclePlateNumberException.java
│   │    │     └── VehicleNotFoundException.java
│   │    ├── ErrorMessage.java
│   │    └── GlobalExceptionHandler.java
│   ├── /util
│   │    └── JwtActions
│   ├── /validator
│   │    ├── PatterLong.java
│   │    └── PatternLongValidator.java
│   ├── BusesApplication.java
│   ├── Command.java
│   └── Query.java 
└── main/resources/
    ├── /keys
    │    ├── private.pem
    │    └── public.pem
    ├── /static
    ├── /templates
    ├── application.yml  # Application configuration
    └── banner.txt
```

### Building

```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Create JAR
./mvnw clean package
```

## Technical Details

---

- **Framework**: Spring Boot 3.4.5
- **Security Framework**: Spring oauth2
- **Database**: MySQL 9.2
- **Build Tool**: Maven
- **Java Version**: 21

## Contributing

This is primarily a learning project. Feel free to fork and experiment with your own modifications!

---

*This project was created as a learning exercise to explore Spring Boot's capabilities, and
practical
application development.*