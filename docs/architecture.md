## This is buses project structure

I know that this structure need some work this is it for now.

```
src/
├── main/java/com/marwan/travelSafe/buses
│   ├── configuration/                                               # Programming Application configuration
│   │    ├── cache/
│   │    │     └── CacheConfiguration.java                           # cache config
│   │    └── security/
│   │          ├── BCryptPasswordConfig                              # password encoder config
│   │          ├── JwtConfig                                         # Jwt encoder and decoder
│   │          └── SecurityConfig                                    # spring security config
│   ├── domain/                                                      # Domain package holds main project code
│   │    ├── department/
│   │    │     ├── /controller                                       # department controller package
│   │    │     │     ├── DepartmentController.java
│   │    │     │     └── DepartmentsController.java
│   │    │     ├── /model                                            # department model package
│   │    │     │     ├── /DTO                                        # department dto package
│   │    │     │     │     ├── CreateDepartmentDto.java
│   │    │     │     │     ├── DepartmentResponseDTO.java
│   │    │     │     │     ├── PromteManagerDto.java
│   │    │     │     │     └── UpdateDepartmentDTO.java
│   │    │     │     ├── Department.java                             # department entity class
│   │    │     │     └── DepartmentMapper.java
│   │    │     ├── /service                                          # department service package
│   │    │     │     ├── CreateDepartmentService.java
│   │    │     │     ├── DeleteDepartmentService.java
│   │    │     │     ├── GetDepartmentService.java
│   │    │     │     ├── GetDepartmentsService.java
│   │    │     │     ├── PromoteManagerService.java
│   │    │     │     └── UpdateDepartmentService.java
│   │    │     └── DepartmentRepository.java                         # department repository interface
│   │    ├── employee/
│   │    │     ├── /controller                                       # employee controller package
│   │    │     │     ├── EmployeeAuthenticationController.java
│   │    │     │     ├── EmployeeController.java
│   │    │     │     └── EmployeesController.java
│   │    │     ├── /model                                            # employee model package
│   │    │     │     ├── /dto                                        # employee dto package
│   │    │     │     │     ├── EmployeeLoginDTO.java
│   │    │     │     │     ├── EmployeePayCheckDTO.java
│   │    │     │     │     ├── EmployeeResponseDTO.java
│   │    │     │     │     └── InputEmployeeDTO.java
│   │    │     │     ├── /enums                                      # employee roles enum package
│   │    │     │     │     └── Role.java
│   │    │     │     ├── /projection                                 # employee projection package
│   │    │     │     │     └── EmployeeAuth.java
│   │    │     │     ├── Emoloyee.java                               # employee entity class
│   │    │     │     └── EmployeeMapper.java
│   │    │     ├── /service                                          # employee service package
│   │    │     │     ├── /get                                        # employee get service package
│   │    │     │     │     ├── CountEmployeesService.java
│   │    │     │     │     ├── GetEmployeesByDepartmentIdService.java
│   │    │     │     │     ├── GetEmployeesBySupervisorService.java
│   │    │     │     │     ├── GetEmployeeService.java
│   │    │     │     │     └── GetEmployeesService.java
│   │    │     │     ├── CreateEmployeeService.java
│   │    │     │     ├── DeleteEmployeeService.java
│   │    │     │     ├── EmployeeLoginService.java
│   │    │     │     └── UpdateEmployeeServicee.java
│   │    │     └── EmployeeRepository.java                          # employee repository interface
│   │    ├── /seats
│   │    │     ├── /model                                           # seats model package
│   │    │     │     ├── /dto                                       # seats dto package
│   │    │     │     │     ├── BookSeatDTO.java
│   │    │     │     │     ├── SeatResponseDTO.java
│   │    │     │     │     └── UpdateTripSeatDTO.java
│   │    │     │     ├── Seat.java                                  # seats entity class
│   │    │     │     └── SeatId.java                                # seats ID class
│   │    │     ├── /service                                         # department controller package
│   │    │     │     ├── BookSeatService.java
│   │    │     │     └── SearchForSeatsByTripIdService.java
│   │    │     ├── SeatController.java                              # seat Controller class
│   │    │     └── SeatRepository.java                              # seat repository interface
│   │    ├── /travelLine
│   │    │     ├── /model                                           # department model package
│   │    │     │     ├── /dto                                       # travel line dto package
│   │    │     │     │    ├── InputTravelLineDTO.java
│   │    │     │     │    └── TravelLineResponseDTO.java
│   │    │     │     └── TravelLine.java                            # travel line entity class
│   │    │     ├── /service                                         # travel line service package
│   │    │     │     ├── CreateTravelLineService.java
│   │    │     │     ├── DeleteTravelLineService.java
│   │    │     │     ├── GetTravelLineService.java
│   │    │     │     ├── GetTravelLinesService.java
│   │    │     │     └── UpdateTravelLineService.java
│   │    │     ├── TravelLineController.java                        # travel line entity class
│   │    │     └── TravelLineRepository.java                        # travel line repository interface
│   │    ├── /trip
│   │    │     ├── /model                                           # trip model package
│   │    │     │     ├── /dto                                       # trip dto package
│   │    │     │     │     ├── CreateTripDTO.java
│   │    │     │     │     ├── TripAvailabilityDTO.java
│   │    │     │     │     ├── TripResponseDTO.java
│   │    │     │     │     ├── TripSearchDTO.java
│   │    │     │     │     └── UpdateTripDTO.java
│   │    │     │     ├── /projection                                # trip projection package
│   │    │     │     │     └── TripAvailabilityProjection.java
│   │    │     │     ├── Trip.java                                  # trip entity class
│   │    │     │     └── TripMapper.java
│   │    │     ├── /service                                         # trip service package
│   │    │     │     ├── CreateTripService.java
│   │    │     │     ├── DeleteTripService.java
│   │    │     │     ├── GetTripService.java
│   │    │     │     ├── SearchForTripsService.java
│   │    │     │     └── UpdateTripService.java
│   │    │     ├── TripController.java                              # trip controller class 
│   │    │     └── TripRepository.java                              # trip repository interface
│   │    └── /vehicle
│   │          ├── /model                                           # veichle model package
│   │          │     ├── /dto                                       # veichle dto package
│   │          │     │     ├── CreateVehicleDTO.java
│   │          │     │     ├── UpdateVehicleDTO.java
│   │          │     │     └── VehicleResponseDTO.java
│   │          │     ├── Vehicle.java                               # vehicle entity class
│   │          │     └── VehicleMapper.java
│   │          ├── /service                                         # vehicle service package
│   │          │     ├── CreateVehicleService.java
│   │          │     ├── DeleteVehicleService.java
│   │          │     ├── GetVehicleService.java
│   │          │     ├── GetVehiclesleService.java
│   │          │     └── UpdateVehicleService.java
│   │          ├── VehicleController.java                           # vehicle contoller class
│   │          └── VehicleRepository.java                           # vehicle repository interface
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
│   ├── BusesApplication.java                                       # Application entry point
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