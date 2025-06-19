# Transportation management system

A simple back end api project using spring boot, spring data jpa, spring security and
MySQL for a database built for learning purposes,it's under development right now.
You can see it in more structure way with this
tool [![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/Marwan-Mamdouh/transportation-company-management-system)

## Features

CRUD operations for employees, departments, vehicles, trips, and seat booking.

## Requirements

- **Java 21** or higher
- **Maven 3.6+** (if building from source)

## Quick Start

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

4. Or run the app with one command(there is no need for no.2 or 3 if you do this):
   ```bash
   ./mvnw spring-boot:run
   ```

## Usage

The application runs as a web-server.
this part is under development right now and all we have is Swagger-ui

[//]: # (Use the following end-points &#40;APIs&#41; with their arguments:)

## Data Storage

- Expenses are stored in `MySQL` database living on a docker container in your home directory
- The Schema will be created automatically when you run the app on your machine for the first time

## Development

### Project Structure

You can view the project structure [here](docs/architecture.md)

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