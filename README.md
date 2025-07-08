# S04T02N01 - CRUD with H2 and Spring Boot (Using DTOs)

This Spring Boot project implements a CRUD (Create, Read, Update, Delete) for a **Fruit** entity, persisted in an **H2** in-memory database. The project follows the MVC pattern, REST best practices, and uses DTOs (Data Transfer Objects) to separate API from persistence layers.

---

## Task Objective

- Build a REST API with Spring Boot and H2 database.
- Use proper HTTP verbs and response codes.
- Implement DTOs for input/output data.
- Centralize exception handling with `GlobalExceptionHandler`.

---

## Requirements

- Java JDK 11 or higher
- Maven or Gradle
- IDE (e.g., IntelliJ IDEA, Eclipse)
- Postman or similar HTTP client for testing

---

## Project Structure

- `cat.itacademy.s04.t02.n01.controllers` — REST Controllers
- `cat.itacademy.s04.t02.n01.model` — JPA Entities and Models
- `cat.itacademy.s04.t02.n01.dto` — Data Transfer Objects
- `cat.itacademy.s04.t02.n01.services` — Business Logic / Services
- `cat.itacademy.s04.t02.n01.repository` — JPA Repositories
- `cat.itacademy.s04.t02.n01.exception` — Global Exception Handling

---

## Fruit Entity Properties

| Property      | Type         | Description                 |
|---------------|--------------|-----------------------------|
| `id`          | `long`       | Primary key (auto-generated)|
| `name`        | `String`     | Name of the fruit           |
| `weightKilos` | `BigDecimal` | Weight in kilos             |

---

## REST API Endpoints

| HTTP Method | Endpoint              | Description                   |
|-------------|-----------------------|-------------------------------|
| POST        | `/fruits/add`         | Create a new fruit            |
| PUT         | `/fruits/update`      | Update an existing fruit      |
| DELETE      | `/fruits/delete/{id}` | Delete a fruit by ID          |
| GET         | `/fruits/getOne/{id}` | Get a fruit by ID             |
| GET         | `/fruits/getAll`      | Get all fruits                |

---

## H2 Database

- Uses an in-memory H2 database for quick development/testing.
- Access the H2 console (optional): `http://localhost:8080/h2-console`
- Default username: `sa`

---

## Validation & Exception Handling

- Input validation is performed on DTOs using annotations like `@NotNull`, `@Min`.
- Validation errors return HTTP 400 with detailed messages.
- Non-existent fruit requests return HTTP 404.
- Global exceptions are managed via `GlobalExceptionHandler` for consistent responses.

---

## Running the Project

1. Clone the repository:

   ```bash
   git clone <https://github.com/AdriaVH/4.2-ApiRest-Spring-N1.git>
   
2. Open the project in your favorite IDE or terminal.

3. Run the project using Maven:

mvn spring-boot:run

4. Access the API at:

http://localhost:8080/api/fruits/
