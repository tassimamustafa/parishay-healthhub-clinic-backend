# Parishay HealthHub Clinic – Backend (Spring Boot)

Parishay HealthHub Clinic is a Spring Boot–based backend for a small clinic / health hub.  
It manages patients, doctors, employees, appointments, visits (prescriptions), sales, payroll, and employee attendance.

This project is built as a learning + portfolio project to practice clean REST API design, validation, and modular backend architecture.

---

## Tech Stack

- Java 17 (or your version)
- Spring Boot (Web, JPA, Validation)
- Hibernate / JPA
- MySQL
- Maven
- Spring Tool Suite (STS) / Eclipse
- Postman for API testing

---

## Database & Configuration

The application uses **MySQL** as the database and is configured via `application.properties`.

Current configuration:

```properties
spring.application.name=ParishayHealthHub
server.port=8083

spring.datasource.url=jdbc:mysql://localhost:3306/parishay_health_hub
spring.datasource.username=root
spring.datasource.password=**

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

Notes:

- Database name: `parishay_health_hub`  
- MySQL host: `localhost`, port: `3306`  
- Spring Boot runs on port: `8083`  
- `ddl-auto=update` means schema is updated automatically based on entities (for development use).  
- For security, the real password is not shown in this README (replace `********` with your local password in `application.properties` only).

To run this project locally, you need:

1. A MySQL server running with a database named `parishay_health_hub`.  
2. A MySQL user (e.g. `root`) with access to that database.  

---

## Main Features

- Patient management (CRUD)
- Employee management (CRUD + payroll snapshot)
- Doctor management (CRUD)
- Appointment scheduling (patient ↔ doctor, status: BOOKED / COMPLETED / CANCELLED / NO_SHOW)
- Visit / prescription records linked to patients and doctors
- Sales / billing (consultation, medicines, lab, etc.)
- Employee attendance (Present / Absent / Leave with date)
- Payroll records (per employee per month, including net salary and payment info)
- Basic reporting/filter endpoints for appointments, visits, sales, attendance, and payrolls

---

## API Modules (High Level)

### Patients

- `POST /api/patients` – create patient  
- `GET /api/patients` – list all  
- `GET /api/patients/{id}` – get by id  
- `PUT /api/patients/{id}` – update  
- `DELETE /api/patients/{id}` – delete  

### Employees

- `POST /api/employees`  
- `GET /api/employees`  
- `GET /api/employees/{id}`  
- `PUT /api/employees/{id}`  
- `DELETE /api/employees/{id}`  

### Doctors

- `POST /api/doctors`  
- `GET /api/doctors`  
- `GET /api/doctors/{id}`  
- `PUT /api/doctors/{id}`  
- `DELETE /api/doctors/{id}`  

### Appointments

- `POST /api/appointments` – create appointment  
- `GET /api/appointments` – list all  
- `GET /api/appointments/{id}` – get by id  
- `PUT /api/appointments/{id}` – update  
- `DELETE /api/appointments/{id}` – delete  

Filters / extra:

- `GET /api/appointments/by-patient?patientId=...`  
- `GET /api/appointments/by-doctor?doctorId=...`  
- `GET /api/appointments/by-doctor-and-date?doctorId=...&date=YYYY-MM-DD`  
- `GET /api/appointments/by-status?status=BOOKED`  
- `PUT /api/appointments/{id}/link-sale?saleId=...` – link appointment to sale  

### Visits (Prescriptions)

- `POST /api/visits`  
- `GET /api/visits`  
- `GET /api/visits/{id}`  
- `PUT /api/visits/{id}`  
- `DELETE /api/visits/{id}`  

Filters:

- `GET /api/visits/by-patient?patientId=...`  
- `GET /api/visits/by-doctor?doctorId=...`  
- `GET /api/visits/by-appointment?appointmentId=...`  

### Sales / Billing

- `POST /api/sales`  
- `GET /api/sales`  
- `GET /api/sales/{id}`  
- `PUT /api/sales/{id}`  
- `DELETE /api/sales/{id}`  

Reports:

- `GET /api/sales/today/total` – today’s total sales  
- `GET /api/sales/total?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` – total between dates  
- `GET /api/sales/between?startDate=...&endDate=...` – list between dates  
- `GET /api/sales/doctor?name=DoctorName` – sales by doctor  
- `GET /api/sales/doctor/total?name=DoctorName` – doctor-wise total  
- `GET /api/sales/payment?method=Cash` – sales by payment method  
- `GET /api/sales/payment/total?method=Cash` – payment method total  

### Employee Attendance

- `POST /api/attendance`  
- `GET /api/attendance`  
- `GET /api/attendance/{id}`  
- `PUT /api/attendance/{id}`  
- `DELETE /api/attendance/{id}`  

Reports:

- `GET /api/attendance/date?date=YYYY-MM-DD` – attendance for a date  
- `GET /api/attendance/employee?name=...&startDate=...&endDate=...` – attendance for employee in range  
- `GET /api/attendance/employee/count?name=...&startDate=...&endDate=...` – present/absent counts  

### Payroll

- `POST /api/payrolls`  
- `GET /api/payrolls`  
- `GET /api/payrolls/{id}`  
- `PUT /api/payrolls/{id}`  
- `DELETE /api/payrolls/{id}`  

Filters:

- `GET /api/payrolls/by-employee?employeeId=...`  
- `GET /api/payrolls/by-month?payMonth=YYYY-MM`  

---

## Validation & Error Handling (Short Overview)

The backend uses **Jakarta Bean Validation** on all write operations to keep data clean and consistent.

- Entities (Patient, Employee, Doctor, Visit, Sale, Appointment, EmployeeAttendance, PayrollRecord) use annotations like:
  - `@NotBlank`, `@NotNull`, `@Positive`, `@PositiveOrZero`, `@Size`  
- Controllers use `@Valid @RequestBody ...` on `POST` and `PUT` methods to trigger validation.  

There is a global `RestControllerAdvice` (`GlobalExceptionHandler`) that catches `MethodArgumentNotValidException` and returns a **field-wise error map** with HTTP 400:

```json
{
  "fieldName": "Validation error message"
}
```

Example (invalid patient):

```json
{
  "fullName": "Full name is required",
  "age": "Age must be positive"
}
```

This makes it easy for the frontend to show errors next to the correct form fields and keeps error responses consistent across all modules.

---

## How to Run (Local)

1. Clone the repository:

```bash
git clone https://github.com/tassimamustafa/parishay-healthhub-clinic-backend.git
cd parishay_healthhub_clinic_backend
```

2. Create the MySQL database:

```sql
CREATE DATABASE parishay_health_hub;
```

3. Update `src/main/resources/application.properties` with your local MySQL password (username, URL, and port are already configured):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parishay_health_hub
spring.datasource.username=root
spring.datasource.password=your_local_password_here
```

4. Build & run the application:

```bash
mvn spring-boot:run
```

or run the main Spring Boot application class from STS / Eclipse.

5. Test APIs with Postman:

- Base URL: `http://localhost:8083`
- Example: `GET http://localhost:8083/api/patients`

---

## Status

This project is still in active development and is mainly used for learning, practicing backend design, and building a portfolio‑ready clinic management backend.

Contributions and feedback are welcome.