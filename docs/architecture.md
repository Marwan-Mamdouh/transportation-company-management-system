## This is buses project structure

I know that this structure need some work this is it for now.

```
src/
├── main/java/com/travel/safe/buses
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
│   │    │     ├── /dto                                              # Department dto package
│   │    │     │    ├── CreateDepartmentDto.java
│   │    │     │    ├── DepartmentResponseDTO.java
│   │    │     │    ├── DepartmentSearchDTO.java
│   │    │     │    ├── PromteManagerDTO.java
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
│   │    │     ├── DepartmentsController.java
│   │    │     ├── DepartmentMapper.java                       
│   │    │     └── DepartmentRepository.java                         # Department repository interface
│   │    ├── /employee
│   │    │     ├── /dto                                              # Employee dto package
│   │    │     │    ├── CreateEmployeeDTO.java 
│   │    │     │    ├── DtoResponseI.java 
│   │    │     │    ├── EmployeeLoginDTO.java
│   │    │     │    ├── EmployeePayCheckDTO.java
│   │    │     │    ├── EmployeeRequestDTO.java
│   │    │     │    ├── EmployeeResponseDTO.java
│   │    │     │    └── EmployeeGroupRequestDTO.java
│   │    │     ├── /enums                                            # Employee roles enum package
│   │    │     │    └── Role.java
│   │    │     ├── /exceptions                                       # Employee exception package             
│   │    │     │    ├── DuplicateEmployeeDataException.java
│   │    │     │    ├── EmployeeNotFoundException.java
│   │    │     │    ├── NotValidPasswordException.java
│   │    │     │    └── SupervisorNotFoundException.java
│   │    │     ├── /model                                           # Employee model package
│   │    │     │    ├── Emoloyee.java                               # Employee entity class
│   │    │     │    └── EmployeeAuth.java
│   │    │     ├── /service                                         # Employee service package
│   │    │     │    ├── /get                                        # Employee get service package
│   │    │     │    │     ├── CountEmployeesService.java
│   │    │     │    │     ├── GetEmployeesByService.java
│   │    │     │    │     ├── GetEmployeeService.java
│   │    │     │    │     └── GetEmployeesService.java
│   │    │     │    ├── CreateEmployeeService.java
│   │    │     │    ├── DeleteEmployeeService.java
│   │    │     │    ├── EmployeeLoginService.java
│   │    │     │    └── UpdateEmployeeServicee.java
│   │    │     ├── EmployeesController.java
│   │    │     ├── EmployeeMapper.java 
│   │    │     ├── EmployeeRepository.java                          # Employee repository interface
│   │    │     └── EmployeeSpecification.java                           
│   │    ├── /seats
│   │    │     ├── /dto                                             # Seats dto package
│   │    │     │    ├── BookSeatDTO.java
│   │    │     │    └── SeatResponseDTO.java
│   │    │     ├── /exceptions                                       # Seat exception package
│   │    │     │    ├── NotAvaialbleSeatsFoundException.java
│   │    │     │    └── SeatAlreadyBookedException.java
│   │    │     ├── /model                                            # Seat model package
│   │    │     │    ├── Seat.java                                    # Seat entity class
│   │    │     │    └── SeatId.java                                  # Seat ID class
│   │    │     ├── /service                                          # Seat controller package
│   │    │     │    ├── BookSeatService.java
│   │    │     │    └── SearchForSeatsByTripIdService.java
│   │    │     ├── SeatMapper.java
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
│   │    │     ├── TravelLineMapper.java
│   │    │     └── TravelLineRepository.java                         # Travel Line repository interface
│   │    ├── /trip
│   │    │     ├── /DTO                                              # Trip dto package
│   │    │     │     ├── CreateTripDTO.java
│   │    │     │     ├── TripAvailabilityDTO.java
│   │    │     │     ├── TripResponseDTO.java
│   │    │     │     ├── TripSearchDTO.java
│   │    │     │     └── UpdateTripDTO.java
│   │    │     ├── /exceptions                                       # Trip exception package
│   │    │     │     ├── TripNotFoundException.java
│   │    │     │     └── TripNotValidException.java
│   │    │     ├── /model                                            # Trip model package 
│   │    │     │     ├── Trip.java                                   # Trip entity class
│   │    │     │     └── TripAvailabilityProjection.java
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