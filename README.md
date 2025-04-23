- [Overview](#overview)
- [Running](#running)
  - [Requirements](#requirements)
  - [1. Clone the Repository](#1-clone-the-repository)
  - [2. Configure the Environment](#2-configure-the-environment)
  - [3. Run the Application](#3-run-the-application)
- [Using](#using)
  - [Authentication](#authentication)
  - [Example Endpoints](#example-endpoints)
- [Testing](#testing)

---

## Overview
A modern, secure authentication and authorization API built with **Spring Boot 3**, **Java 21**, and **JWT**. This project provides a scalable foundation for role-based access control and user management using RESTful standards.

This project implements:

- Stateless authentication with **JSON Web Tokens (JWT)**
- **User registration** and **login** endpoints
- **Role-based access control (RBAC)** using Spring Security
- Full **CRUD for users and roles (authorities)**
- Clean architecture with **DTOs**, services, and repositories
- Exception handling and HTTP status mapping

---

## Running

### Requirements

- Java 21+
- Maven 3.9+
- PostgreSQL or Docker

### 1. Clone the Repository

```bash
git clone https://github.com/joaofalonso/spring-auth.git
cd spring-auth
```

### 2. Configure the Environment

Update database connection details in `src/main/resources/application.yml`.



### 3. Run the Application

```bash
sudo docker compose up --build
```

API will be available at: `http://localhost:8080`

---

## Using

### Authentication

- **Register:**  
  `POST /api/auth/register`

  ```json
  {
    "name": "name",
    "email": "name.email@example.com",
    "password": "supersecret"
  }
  ```

- **Login:**  
  `POST /api/auth/login`

  ```json
  {
    "email": "name.email@example.com",
    "password": "supersecret"
  }
  ```

  Response:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR..."
  }
  ```

- Use this token in protected endpoints:
  ```
  Authorization: Bearer <your_token>
  ```

---

### Example Endpoints

| Method | Endpoint                | Description                      | Auth Required |
|--------|-------------------------|----------------------------------|---------------|
| POST   | `/api/auth/login`       | Authenticate and receive token   | ❌            |
| GET    | `/api/users`            | List all users                   | ✅ Admin      |
| GET    | `/api/users/{id}`       | Get user by ID                   | ✅            |
| PUT    | `/api/users/{id}`       | Update user info                 | ✅            |
| DELETE | `/api/users/{id}`       | Delete user                      | ✅ Admin      |


---

## Testing


```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer <your_token>"
```


This project is licensed under the MIT License.

```
