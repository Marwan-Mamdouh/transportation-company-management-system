## This is buses project structure

I know that this structure need some work this is it for now.

```
src/
├── main/java/com/marwan/travelSafe/buses
│   ├── /comman
│   │    ├── /configuration                                          # Programming Application configuration
│   │    │      ├── /cache                                           # Cache configuration package 
│   │    │      │   └── CacheConfiguration.java                     
│   │    │      └── /security                                        # Security configuration package
│   │    │          ├── BCryptPasswordConfig.java                    
│   │    │          ├── JwtConfig.java                               
│   │    │          └── SecurityConfig.java                          
│   │    ├── /exceptions                                             # Global exceptions configuration package
│   │    │      ├── ErrorMessage.java
│   │    │      └── GlobalExceptionHandler.java
│   │    ├── /shared                                                 # Shared interfaces package
│   │    │      ├── Query.java 
│   │    │      └── Command.java
│   │    ├── /util                                                   # Util package
│   │    │      └── JwtActions
│   │    └── /validator                                              # Custom validator package
│   │           ├── PatterLong.java
│   │           └── PatternLongValidator.java
│   ├── /domain                                                      # Domain package holds main project code
│   │    ├── /department
│   │    │     ├── /controller                                       # Department controller package
│   │    │     │    ├── DepartmentController.java
│   │    │     │    └── DepartmentsController.java
│   │    │     ├── /DTO                                              # Department dto package
│   │    │     │    ├── CreateDepartmentDto.java
│   │    │     │    ├── DepartmentResponseDTO.java
│   │    │     │    ├── PromteManagerDto.java
│   │    │     │    └── UpdateDepartmentDTO.java
│   │    │     ├── /exceptions                                       # Department exception package             
│   │    │     │    └── DepartmentNotFoundException.java
│   │    │     ├── /model                                            # Department model package
│   │    │     │    └── Department.java                              # Department entity class
│   │    │     ├── /service                                          # Department service package
│   │    │     │    ├── CreateDepartmentService.java
│   │    │     │    ├── DeleteDepartmentService.java
│   │    │     │    ├── GetDepartmentService.java
│   │    │     │    ├── GetDepartmentsService.java
│   │    │     │    ├── PromoteManagerService.java
│   │    │     │    └── UpdateDepartmentService.java
│   │    │     ├── DepartmentMapper.java                       
│   │    │     └── DepartmentRepository.java                         # Department repository interface
│   │    ├── /employee
│   │    │     ├── /controller                                       # Employee controller package
│   │    │     │    ├── EmployeeAuthenticationController.java
│   │    │     │    ├── EmployeeController.java
│   │    │     │    └── EmployeesController.java
│   │    │     ├── /dto                                              # Employee dto package
│   │    │     │    ├── EmployeeLoginDTO.java
│   │    │     │    ├── EmployeePayCheckDTO.java
│   │    │     │    ├── EmployeeResponseDTO.java
│   │    │     │    └── InputEmployeeDTO.java
│   │    │     ├── /enums                                            # Employee roles enum package
│   │    │     │    └── Role.java
│   │    │     ├── /exceptions                                       # Employee exception package             
│   │    │     │    ├── DuplicateEmployeeEmailException.java
│   │    │     │    ├── DuplicateEmployeeIdException.java
│   │    │     │    ├── DuplicateEmployeePhoneNumberException.java
│   │    │     │    ├── EmailNotFoundException.java
│   │    │     │    ├── EmployeeNotFoundException.java
│   │    │     │    ├── NotValidPasswordException.java
│   │    │     │    └── SupervisorNotFoundException.java
│   │    │     ├── /model                                            # Employee model package
│   │    │     │     ├── Emoloyee.java                               # Employee entity class
│   │    │     │     └── EmployeeAuth.java
│   │    │     ├── /service                                          # Employee service package
│   │    │     │     ├── /get                                        # Employee get service package
│   │    │     │     │     ├── CountEmployeesService.java
│   │    │     │     │     ├── GetEmployeesByDepartmentIdService.java
│   │    │     │     │     ├── GetEmployeesBySupervisorService.java
│   │    │     │     │     ├── GetEmployeeService.java
│   │    │     │     │     └── GetEmployeesService.java
│   │    │     │     ├── CreateEmployeeService.java
│   │    │     │     ├── DeleteEmployeeService.java
│   │    │     │     ├── EmployeeLoginService.java
│   │    │     │     └── UpdateEmployeeServicee.java
│   │    │     ├── EmployeeMapper.java 
│   │    │     └── EmployeeRepository.java                           # Employee repository interface
│   │    ├── /seats
│   │    │     ├── /dto                                              # Seats dto package
│   │    │     │    ├── BookSeatDTO.java
│   │    │     │    ├── SeatResponseDTO.java
│   │    │     │    └── UpdateTripSeatDTO.java
│   │    │     ├── /exceptions                                       # Seat exception package
│   │    │     │    ├── SeatAlreadyBookedException.java
│   │    │     │    └── SeatNotFoundException.java
│   │    │     ├── /model                                            # Seat model package
│   │    │     │    ├── Seat.java                                    # Seat entity class
│   │    │     │    └── SeatId.java                                  # Seat ID class
│   │    │     ├── /service                                          # Seat controller package
│   │    │     │    ├── BookSeatService.java
│   │    │     │    └── SearchForSeatsByTripIdService.java
│   │    │     ├── SeatController.java                               # Seat Controller class
│   │    │     └── SeatRepository.java                               # Seat repository interface
│   │    ├── /travelLine
│   │    │     ├── /dto                                              # travel line dto package
│   │    │     │    ├── InputTravelLineDTO.java
│   │    │     │    └── TravelLineResponseDTO.java
│   │    │     ├── /exceptions                                       # Travel Line exception package
│   │    │     │    ├── TravelLineNotFoundException.java
│   │    │     │    └── TravelLineNotValidException.java
│   │    │     ├── /model                                            # Travel Line model package
│   │    │     │    └── TravelLine.java                              # Travel Line entity class
│   │    │     ├── /service                                          # Travel Line service package
│   │    │     │    ├── CreateTravelLineService.java
│   │    │     │    ├── DeleteTravelLineService.java
│   │    │     │    ├── GetTravelLineService.java
│   │    │     │    ├── GetTravelLinesService.java
│   │    │     │    └── UpdateTravelLineService.java
│   │    │     ├── TravelLineController.java                         # Travel Line entity class
│   │    │     └── TravelLineRepository.java                         # Travel Line repository interface
│   │    ├── /trip
│   │    │     ├── /DTO                                              # Trip dto package
│   │    │     │     ├── CreateTripDTO.java
│   │    │     │     ├── TripAvailabilityDTO.java
│   │    │     │     ├── TripResponseDTO.java
│   │    │     │     ├── TripSearchDTO.java
│   │    │     │     └── UpdateTripDTO.java
│   │    │     ├── /model                                            # Trip model package 
│   │    │     │     ├── Trip.java                                   # Trip entity class
│   │    │     │     └── TripAvailabilityProjection.java
│   │    │     ├── /exceptions                                       # Trip exception package
│   │    │     │     ├── TripNotFoundException.java
│   │    │     │     └── TripNotValidException.java
│   │    │     ├── /service                                          # Trip service package
│   │    │     │     ├── CreateTripService.java
│   │    │     │     ├── DeleteTripService.java
│   │    │     │     ├── GetTripService.java
│   │    │     │     ├── SearchForTripsService.java
│   │    │     │     └── UpdateTripService.java
│   │    │     ├── TripController.java                               # Trip controller class 
│   │    │     ├── TripMapper.java 
│   │    │     └── TripRepository.java                               # Trip repository interface
│   │    └── /vehicle
│   │          ├── /dto                                              # Vehicle dto package
│   │          │    ├── CreateVehicleDTO.java
│   │          │    ├── UpdateVehicleDTO.java
│   │          │    └── VehicleResponseDTO.java
│   │          ├── /exceptions                                       # Vehicle exception package
│   │          │     ├── DuplicateVehiclePlateNumberException.java
│   │          │     └── VehicleNotFoundException.java
│   │          ├── /model                                            # Vehicle model package
│   │          │     └── Vehicle.java                                # Vehicle entity class
│   │          ├── /service                                          # Vehicle service package
│   │          │     ├── CreateVehicleService.java
│   │          │     ├── DeleteVehicleService.java
│   │          │     ├── GetVehicleService.java
│   │          │     ├── GetVehiclesleService.java
│   │          │     └── UpdateVehicleService.java
│   │          ├── VehicleController.java                            # vehicle contoller class
│   │          ├── VehicleMapper.java
│   │          └── VehicleRepository.java                            # vehicle repository interface
│   ├── BusesApplication.java                                        # Application entry point
└── main/resources/
    ├── /keys
    │    ├── private.pem
    │    └── public.pem
    ├── /static
    ├── /templates
    ├── application.yml  # Application configuration
    └── banner.txt
```