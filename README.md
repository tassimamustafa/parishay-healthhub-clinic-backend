# Parishay HealthHub – Clinic Backend

Spring Boot REST API for managing a clinic (patients, doctors, appointments, visits, sales, payroll, etc.).  
Secured using Spring Security with database-backed user authentication.

## Tech Stack

- Java 17
- Spring Boot 4
- Spring Web (REST)
- Spring Data JPA (Hibernate)
- Spring Security (HTTP Basic)
- MySQL 8
- Maven

## How to Run Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/tassimamustafa/parishay-healthhub-clinic-backend.git
   cd parishay-healthhub-clinic-backend
   ```

2. Configure database in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/parishay_health_hub
   spring.datasource.username=root
   spring.datasource.password=YOUR_DB_PASSWORD

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Run the application:

   - From IDE: run `ParishayHealthHubApplication`  
   - Or from command line:

     ```bash
     mvn spring-boot:run
     ```

4. The API will start on:

   ```text
   http://localhost:8083
   ```

## Authentication

The API is secured with **HTTP Basic Authentication**.  
User credentials are stored in the `app_user` table.

Example user (must exist in DB):

```text
username: admin
password: admin123
```

To test with Postman or any HTTP client:

- Set **Auth Type** = Basic Auth
- Username = `admin`
- Password = `admin123`

All endpoints require authentication (`anyRequest().authenticated()`).

## Core Endpoints (Appointments)

Base path:

```text
http://localhost:8083/api/appointments
```

All endpoints must be called with Basic Auth.

### Get all appointments

- `GET /api/appointments`  
- Response: list of `Appointment` objects.

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
- Body: same structure as create.

### Delete appointment

- `DELETE /api/appointments/{id}`

### Filter endpoints

- `GET /api/appointments/by-patient?patientId=1`  
- `GET /api/appointments/by-doctor?doctorId=1`  
- `GET /api/appointments/by-doctor-and-date?doctorId=1&date=2026-06-15`  
- `GET /api/appointments/by-status?status=BOOKED`

## Security Configuration (Summary)

- All endpoints require authentication (`anyRequest().authenticated()`).
- Stateless sessions:

  ```java
  .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  ```

- Custom `UserDetailsService` loads users from `AppUser` table.
- Passwords are stored using `BCryptPasswordEncoder`.

## Future Improvements

- Role-based authorization (e.g. ADMIN vs USER).
- JWT-based authentication instead of Basic Auth.
- API documentation with OpenAPI / Swagger.
- Integration tests for secured endpoints.