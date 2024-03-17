# OneAuth Project

This project is a Spring Boot application with OAuth2 resource server capabilities, along with a React user interface. It is protected by Keycloak acitng as the authorization server. The entirity of the application is hosted in Kubernetes and the necessary deployment yamls are stored within their respective folders. The running version of the application can be accessed [here](https://oneauth.theiris.club/).

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
- Testcontainers: Offers lightweight, throwaway instances of common databases for testing.
- Spring Boot Starter Test: Includes testing utilities and dependencies for Spring Boot applications.
- Spring Security Test: Provides testing utilities for Spring Security configurations.

### Frontend (React UI)

The frontend is built using React, a popular JavaScript library for building user interfaces. It provides a user-friendly interface for interacting with the backend API.

## Prerequisites

### Backend

- JDK 17
- Maven
- Docker

### Frontend

- Node.js
- npm (Node Package Manager)

## Getting Started

### Backend

1. Clone this repository.
2. Run `docker-compose up`
3. Set active profile as `local` by setting up `SPRING_PROFILES_ACTIVE=local` in environment variables.
4.

### UI

1. You need to have the Backend running along with `docker-compose up`.
2. Switch to the `frontend` folder.
3. Run `npm install` to install UI dependencies.
4. Run `npm run dev` to run application locally in port `3000`.
