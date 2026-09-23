# CommerceFlow

A full-stack e-commerce platform inspired by modern Indian marketplaces, built as a portfolio project.

## Tech Stack

- Java 25 LTS
- Spring Boot 3.3
- Spring Cloud (Gateway + Eureka)
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Kafka
- React.js (frontend to be added next)

## Backend Architecture

- Discovery Server
- API Gateway
- User Service
- Product Service
- Cart Service
- Order Service
- Payment Service
- Notification Service
- Shared Events Library

## Local Development

1. Start infrastructure:
   ```bash
   docker compose up -d postgres zookeeper kafka
   ```
2. Start the discovery server, gateway, and services in order.
3. Access the gateway at http://localhost:8080

## Notes

This repository begins with the backend foundation and event-driven microservice structure, which is the first step before the frontend UI.
