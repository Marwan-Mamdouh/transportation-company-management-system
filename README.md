# Transportation Company Management System

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A robust, scalable backend REST API designed for managing the complex operations of a transportation company. Built with **Domain-Driven Design (DDD)** principles, this project demonstrates a professional architecture using Spring Boot, JPA, and modern security practices.

## 🚀 Key Features

- **Domain-Driven Design (DDD)**: structured by features (`department`, `employee`, `vehicle`, `trip`, `seat`) rather than technical layers.
- **Role-Based Access Control (RBAC)**: Secure APIs using Spring Security resource servers and JWT validation.
- **Efficient Data Access**: Optimized JPA queries and pagination integration.
- **Comprehensive Management Modules**:
  - **Fleet Management**: Track vehicles, bus features, and maintenance status.
  - **HR & Organization**: Manage departments, employees, and promotions.
  - **Trip Operations**: Schedule trips, manage travel lines, and handle seat bookings.

## 🛠️ Technology Stack

- **Core**: Java 21, Spring Boot 3.4.5
- **Data**: Spring Data JPA, MySQL 9.2
- **Security**: Spring Security (OAuth2 Resource Server), JWT (RSA Signed)
- **Utilities**: Lombok, MapStruct (Zero-overhead mapping)
- **Documentation**: SpringDoc OpenAPI (Swagger UI)
- **Deployment**: Docker Compose

## 🏗️ Architecture

The project follows a **Package-by-Feature** architecture to ensure modularity and high cohesion.

```
src/main/java/com/travel/safe/buses/
├── domain/                  # Core business logic modules
│   ├── department/          # Department feature (Controller, Service, Domain, Repository)
│   ├── employee/            # Employee management
│   ├── vehicle/             # Fleet management
│   └── ...
├── comman/                  # Shared utilities and configurations
│   ├── configuration/       # Security & App config
│   └── exceptions/          # Global exception handling
└── BusesApplication.java    # Entry point
```

## ⚙️ Getting Started

### Prerequisites

- **Java 21** SDK
- **Docker** & **Docker Compose**
- **Maven** (optional, wrapper included)

### 1. Clone the Repository

```bash
git clone https://github.com/Marwan-Mamdouh/transportation-company-management-system.git
cd transportation-company-management-system
```

### 2. Configure Security Keys

> **Security Note**: This project uses RSA keys for JWT signing. You must generate them before running the app.

```bash
# Linux/Mac/Git Bash
mkdir -p src/main/resources/keys
openssl genrsa -out src/main/resources/keys/private.pem 2048
openssl rsa -in src/main/resources/keys/private.pem -pubout -out src/main/resources/keys/public.pem
```

### 3. Database Setup

Start the MySQL database using Docker:

```bash
docker-compose up -d
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## 📖 API Documentation

Once the application is running, you can explore the REST endpoints via the Swagger UI:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

## 🛡️ Configuration

The application uses `application.yml` for configuration. Sensitive values can be overridden via Environment Variables:

| Variable          | Default                             | Description             |
| ----------------- | ----------------------------------- | ----------------------- |
| `DB_URL`          | `jdbc:mysql://localhost:3308/Buses` | Database JDBC URL       |
| `DB_USERNAME`     | `dev`                               | Database user           |
| `DB_PASSWORD`     | `123`                               | Database password       |
| `JWT_PRIVATE_KEY` | `classpath:keys/private.pem`        | Path to RSA private key |

## 📝 Learning Outcomes & Issues

This project was built as a deep dive into Spring Boot ecosystem.
See [ISSUES_REPORT.md](ISSUES_REPORT.md) for a self-reflection on identified improvements and architectural decisions.
