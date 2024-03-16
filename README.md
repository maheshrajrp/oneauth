# Simple OAuth Project

This project serves as a template for setting up a Spring Boot application with OAuth2 resource server capabilities, along with a React user interface.

## Description

The project is divided into two main components:

### Backend (Spring Boot)

The backend is a Spring Boot application configured with the following dependencies:

- Spring Boot Starter Data JPA: Provides easy setup for Spring Data JPA.
- Spring Boot Starter Validation: Adds support for bean validation in Spring Boot applications.
- Spring Boot Starter OAuth2 Resource Server: Configures the application as an OAuth2 resource server, allowing it to validate and process OAuth2 tokens.
- Spring Boot Starter Web: Sets up Spring Web MVC.
- Springdoc OpenAPI Starter WebMVC UI: Enables generation of OpenAPI documentation for RESTful APIs.
- PostgreSQL Driver: Allows the application to interact with a PostgreSQL database.
- Lombok: Provides annotations to reduce boilerplate code.
- Rest Assured: Simplifies testing of RESTful APIs.
- Testcontainers: Offers lightweight, throwaway instances of common databases for testing.
- Spring Boot Starter Test: Includes testing utilities and dependencies for Spring Boot applications.
- Spring Security Test: Provides testing utilities for Spring Security configurations.

### Frontend (React UI)

The frontend is built using React, a popular JavaScript library for building user interfaces. It provides a user-friendly interface for interacting with the backend API.

## Prerequisites

### Backend

- JDK 17
- Maven

### Frontend

- Node.js
- npm (Node Package Manager)

## Getting Started

### Backend

1. Clone this repository.
2. Configure your PostgreSQL database settings in `application.properties`.
3. Build the project using Maven:

```bash
mvn clean install
