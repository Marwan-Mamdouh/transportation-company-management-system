## This is buses project structure
I know that this structure need some work this is it for now.
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