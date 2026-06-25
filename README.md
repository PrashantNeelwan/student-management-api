# Student Management REST API

A production-quality REST API built with Spring Boot demonstrating professional backend development practices.

## Features

- ✅ **CRUD Operations** - Create, Read, Update, Delete students
- ✅ **Validation** - Input validation with proper error messages
- ✅ **Exception Handling** - Global exception handler with custom exceptions
- ✅ **Pagination & Sorting** - Efficiently handle large datasets
- ✅ **Logging** - SLF4J logging for debugging and monitoring
- ✅ **HTTP Status Codes** - Proper REST conventions (201, 204, 404, 409, etc.)
- ✅ **DTOs** - Request/Response data transfer objects
- ✅ **3-Layer Architecture** - Controller → Service → Repository

## Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.5.14
- **Database**: H2 (in-memory for development)
- **Build Tool**: Maven
- **ORM**: JPA/Hibernate

## Project Structure
src/main/java/com/student/student_management/

├── controller/      # REST endpoints

├── service/         # Business logic

├── repository/      # Database access

├── entity/          # JPA entities

├── dto/             # Data transfer objects

└── exception/       # Custom exceptions


## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Installation

1. Clone the repository
```bash
git clone https://github.com/YOUR-USERNAME/student-management-api.git
cd student-management-api
```

2. Run the application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Get All Students (Paginated)

## Validation Rules

- **firstName**: Required, 2-50 characters
- **lastName**: Required, 2-50 characters
- **email**: Required, valid email format, unique
- **age**: Required, minimum 16

## What I Learned

- 3-layer architecture and separation of concerns
- REST API best practices
- Spring Boot fundamentals
- JPA and Hibernate
- Input validation and exception handling
- Global exception handlers
- Pagination and sorting
- Logging with SLF4J
