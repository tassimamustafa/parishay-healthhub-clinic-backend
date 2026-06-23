# Parishay HealthHub – Clinic Backend

Spring Boot REST API for managing a clinic (patients, doctors, appointments, visits, sales, payroll, etc.).  
Secured using Spring Security with database-backed user authentication.

In addition to the main clinic backend, this repository now includes supporting microservices for **service discovery**, **authentication**, and an **API Gateway** for routing.

---

## Tech Stack

- Java 17
- Spring Boot 4
- Spring Web (REST)
- Spring Data JPA (Hibernate)
- Spring Security (HTTP Basic)
- Spring Cloud Netflix Eureka (Service Discovery)
- Spring Cloud Gateway (API Gateway)
- MySQL 8
- Maven

---

## Projects in this repository

- `ParishayHealthHub` (main clinic backend)  
  - Port: `8083`  
  - Base path: `/api/...`  
  - Secured with HTTP Basic.

- `discovery-server`  
  - Netflix Eureka Service Registry.  
  - Port: `8761`  
  - `spring.application.name=discovery-server`  
  - Does not register itself as a client (`register.with.eureka=false`, `fetch-registry=false`).

- `auth-service`  
  - Authentication microservice (separate from main clinic backend).  
  - Port: `8086`  
  - `spring.application.name=auth-service`  
  - Registers with Eureka at `http://localhost:8761/eureka/`.  
  - Demo login (AuthTestController):  
    - Username: `admin`  
    - Password: `1234`  

- `api-gateway`  
  - Spring Cloud Gateway entry point for backend services.  
  - Port: `8088`  
  - `spring.application.name=api-gateway`  
  - Registers with Eureka at `http://localhost:8761/eureka`.  
  - Routes `/auth/**` to `auth-service` via Eureka (`lb://auth-service`).

---

## How to Run Locally (clinic backend only)

1. Clone the repository:

   ```bash
   git clone https://github.com/tassimamustafa/parishay-healthhub-clinic-backend.git
   cd parishay-healthhub-clinic-backend
   ```

2. Configure database in `ParishayHealthHub/src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/parishay_health_hub
   spring.datasource.username=root
   spring.datasource.password=YOUR_DB_PASSWORD

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Run the main clinic application:

   - From IDE: run `ParishayHealthHubApplication`  
   - Or from command line:

     ```bash
     mvn spring-boot:run
     ```

4. The clinic API will start on:

   ```text
   http://localhost:8083
   ```

---

## Authentication (clinic backend – HTTP Basic)

The clinic API is secured with **HTTP Basic Authentication**.  
User credentials are stored in the `app_user` table.

Example user (must exist in DB):

```text
username: admin
password: admin123
```

To test with Postman or any HTTP client:

- Auth Type: Basic Auth  
- Username: `admin`  
- Password: `admin123`  

All endpoints under `http://localhost:8083/api/...` require authentication (`anyRequest().authenticated()`).

---

## Core Endpoints (Appointments – clinic backend)

Base path:

```text
http://localhost:8083/api/appointments
```

All endpoints must be called with Basic Auth.

### Get all appointments

- `GET /api/appointments`

### Get appointment by id

- `GET /api/appointments/{id}`

### Create appointment

- `POST /api/appointments`
- Example JSON body:

  ```json
  {
    "patientId": 1,
    "doctorId": 1,
    "patientName": "Ayesha Khan",
    "doctorName": "Dr. Ahmed Raza",
    "appointmentDateTime": "2026-06-15T10:00:00",
    "status": "BOOKED",
    "reason": "Follow-up checkup",
    "saleId": null
  }
  ```

### Update appointment

- `PUT /api/appointments/{id}`

### Delete appointment

- `DELETE /api/appointments/{id}`

### Filter endpoints

- `GET /api/appointments/by-patient?patientId=1`  
- `GET /api/appointments/by-doctor?doctorId=1`  
- `GET /api/appointments/by-doctor-and-date?doctorId=1&date=2026-06-15`  
- `GET /api/appointments/by-status?status=BOOKED`

---

## Microservices & API Gateway (Auth + Discovery + Gateway)

### Services and ports

- Discovery Server (Eureka): `http://localhost:8761`  
- Auth Service: `http://localhost:8086`  
- API Gateway: `http://localhost:8088`

### Eureka discovery flow

Start services in this order:

1. `discovery-server` (port 8761)  
2. `auth-service` (port 8086)  
3. `api-gateway` (port 8088)

Open Eureka dashboard:

```text
http://localhost:8761
```

You should see these registered applications:

- `discovery-server`
- `auth-service`
- `api-gateway`

### API Gateway route configuration (summary)

Current route:

- Path: `/auth/**`  
- Service ID: `auth-service`  
- URI: `lb://auth-service`

Meaning:

- Any request to `http://localhost:8088/auth/...` is forwarded to `auth-service` discovered via Eureka.

---

## Login flow demo (auth-service via gateway)

Direct call to `auth-service`:

- Method: `POST`  
- URL: `http://localhost:8086/auth/login`  
- Headers:  
  - `Content-Type: application/json`  
- Body:

  ```json
  {
    "username": "admin",
    "password": "1234"
  }
  ```

Call via `api-gateway`:

- Method: `POST`  
- URL: `http://localhost:8088/auth/login`  
- Same headers and body.

Both should return:

- `200 OK` and a success message when credentials are correct.  
- `401 Unauthorized` with “Invalid username or password” when credentials are wrong.

---

## Security Configuration (Summary – clinic backend)

- All clinic endpoints require authentication (`anyRequest().authenticated()`).  
- Stateless sessions:

  ```java
  .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  ```

- Custom `UserDetailsService` loads users from `AppUser` table.
- Passwords stored with `BCryptPasswordEncoder`.

---

## Future Improvements

- Role-based authorization (e.g. ADMIN vs USER).  
- JWT-based authentication instead of Basic Auth for clinic + microservices.  
- More routes through API Gateway (e.g. clinic endpoints, patient service, etc.).  
- API documentation with OpenAPI / Swagger.  
- Integration tests for secured endpoints and gateway routes.