# Inventory API

This is a Spring Boot-based API for managing inventory and tickets in a logistics system. It provides endpoints for user authentication, ticket management, and role-based access control.

## Features

- **User Authentication**: Register and login with JWT-based authentication.
- **Ticket Management**: Create, read, update, and delete tickets.
- **Role-Based Access Control**: Different permissions for `ADMIN` and `USER` roles.
- **Pagination**: Retrieve tickets in paginated format.
- **Swagger Documentation**: Interactive API documentation.

## Technologies Used

- **Spring Boot**: Backend framework.
- **Spring Security**: Authentication and authorization.
- **JWT**: JSON Web Tokens for secure authentication.
- **MySQL**: Database for storing users and tickets.
- **Swagger**: API documentation.
- **Maven**: Dependency management.

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/CarlosLagos27/API-inventory.git
cd API-inventory
```

### 2. Configure the Database

1. Create a MySQL database named `inventory_db`.
2. Update the `application.properties` file with your database credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   ```

### 3. Build and Run the Project

1. Build the project using Maven:

   ```bash
   mvn clean install
   ```

2. Run the application:

   ```bash
   mvn spring-boot:run
   ```

   The application will start on port `8080`.

### 4. Access the API

- **Base URL**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`

## API Endpoints

### Authentication

- **Register User**: `POST /api/auth/register`
- **Login**: `POST /api/auth/login`

### Tickets

- **Get All Tickets**: `GET /api/tickets`
- **Get Ticket by ID**: `GET /api/tickets/{id}`
- **Create Ticket**: `POST /api/tickets`
- **Update Ticket Status**: `PUT /api/tickets/{id}/status`
- **Get Tickets by User**: `GET /api/tickets/my-tickets`
- **Delete Ticket**: `DELETE /api/tickets/{id}`

## Database Schema

### Users Table

| Column    | Type        | Description           |
|-----------|-------------|-----------------------|
| id        | BIGINT      | Primary key           |
| username  | VARCHAR(50) | Unique username       |
| password  | VARCHAR(255)| Encrypted password    |
| role      | VARCHAR(20) | User role (ADMIN/USER)|

### Tickets Table

| Column        | Type            | Description                     |
|---------------|-----------------|---------------------------------|
| id            | BIGINT          | Primary key                     |
| title         | VARCHAR(100)    | Title of the ticket             |
| description   | VARCHAR(500)    | Description of the ticket       |
| status        | VARCHAR(20)     | Status of the ticket            |
| created_at    | DATETIME        | Timestamp of ticket creation    |
| user_id       | BIGINT          | Foreign key to the Users table  |

## Swagger Documentation

You can access the Swagger UI at: `http://localhost:8080/swagger-ui.html`

The Swagger UI provides interactive documentation for all API endpoints, including request/response examples and the ability to test endpoints directly from the browser.

## Example Requests

### Register a User

```bash
curl -X POST "http://localhost:8080/api/auth/register" \
-H "Content-Type: application/json" \
-d '{
  "username": "admin",
  "password": "admin123",
  "role": "ADMIN"
}'
```

### Login

```bash
curl -X POST "http://localhost:8080/api/auth/login" \
-H "Content-Type: application/json" \
-d '{
  "username": "admin",
  "password": "admin123"
}'
```

### Create a Ticket

```bash
curl -X POST "http://localhost:8080/api/tickets" \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <your-jwt-token>" \
-d '{
  "title": "Broken product",
  "description": "The product arrived damaged."
}'
```


