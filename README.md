# Student Management REST API

A RESTful Student Management application developed using Java and Spring Boot. This project provides APIs to create, retrieve, update, delete, and search student records using MySQL as the database.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Maven
- Jakarta Bean Validation
- Swagger / OpenAPI
- JUnit 5 and Mockito

## Features

- Create new student records
- Retrieve all students with pagination
- Retrieve student details by ID
- Update existing student information
- Delete student records
- Search students by name
- Search students by course
- DTO-based request and response handling
- Custom exception handling
- Duplicate email validation
- Input validation
- Unit testing

## Project Structure

```text
src
 ├── main
 │   ├── java/com/workshop/studentmanagement1
 │   │   ├── Studentmanagement1Application.java
 │   │   ├── Student.java
 │   │   ├── StudentController.java
 │   │   ├── StudentService.java
 │   │   ├── StudentRepository.java
 │   │   ├── StudentDTO.java
 │   │   ├── StudentResponseDTO.java
 │   │   ├── StudentNotFoundException.java
 │   │   ├── DuplicateEmailException.java
 │   │   └── GlobalExceptionHandler.java
 │   └── resources
 │       └── application.properties
 └── test
     └── java/com/workshop/studentmanagement1
         ├── Studentmanagement1ApplicationTests.java
         └── StudentServiceTest.java
```

## API Endpoints

Base URL: `/api/v1`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/add` | Add a student |
| GET | `/students` | Retrieve all students with pagination |
| GET | `/students/{id}` | Retrieve student by ID |
| PUT | `/students/{id}` | Update student details |
| DELETE | `/{id}` | Delete student by ID |
| GET | `/student/search?name={name}` | Search by name |
| GET | `/student/search/course?course={course}` | Search by course |

## Pagination Example

```http
GET /api/v1/students?page=0&size=5&sort=id,asc
```

## Database Configuration

This application uses MySQL.

Create a database:

```sql
CREATE DATABASE studentmanage;
```

Configure the following environment variables if needed:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

The application also provides local development defaults in `application.properties`.

## How to Run the Project

1. Clone the repository:

```bash
git clone https://github.com/Viji2805/student-management-api.git
```

2. Navigate into the project directory:

```bash
cd student-management-api
```

3. Configure your MySQL database and credentials.

4. Run the application using Maven:

```bash
mvn spring-boot:run
```

## API Documentation

Swagger / OpenAPI documentation is used to describe the REST APIs.

## Testing

Unit tests are implemented using JUnit 5 and Mockito.

Run tests using:

```bash
mvn test
```

## Author

GitHub: https://github.com/Viji2805
