# Retra-Microservices (WIP)

Retra-Microservices is a microservices-based, message-driven e-commerce platform tailored for retro gaming systems, video games, and related products. This exploratory project implements various microservices design patterns, including database-per-service, circuit breaker, and fault tolerance, to create a robust and scalable architecture. 

The application focuses on a niche market of retro gaming enthusiasts. Users can list, buy, and review purchases in a streamlined and modular environment.

Future work is referenced at the bottom.

## Stacks

### Main Application Stack
This stack comprises the core business logic and services:
- **API Gateway**: Centralized routing and request handling.
- **Discovery Service**: Service registration and discovery using Eureka.
- **Order Service**: Handles order creation, tracking, and management.
- **Product Service**: Manages product listings and details.
- **Review Service**: Facilitates reviews for users after successful transactions.
- **User Service**: Handles user profiles and authentication.
- **RabbitMQ**: Message broker for asynchronous communication.
- **Postgres Database**: Persistent storage for write models.

Built with **Java** and **Spring Boot** (version `3.3.1`).

### Monitoring Stack
Tools for observability and system health monitoring:
- **Grafana**: Visualizes metrics and system performance.
- **cAdvisor**: Monitors container resource usage.
- **Prometheus**: Collects and queries metrics.
- **Node Exporter**: Exposes hardware and OS metrics.

### Logging & Tracing Stack
Ensures robust logging and distributed tracing:
- **Elastic Search**: Stores and indexes logs.
- **Logstash**: Collects and processes logs.
- **Kibana**: Visualizes logs and metrics.
- **Zipkin**: Enables distributed tracing for microservices.

## Utilization
### API Gateway
All business-related requests pass through the API Gateway, accessible at:

http://<node-ip>:8080

### Available Endpoints
A complete collection of available endpoints is documented using OpenAPI (Swagger). Access it here:

Swagger Documentation: [Swagger URL]

### Observability Services
Direct access to visual interfaces for monitoring and tracing:

Grafana: [Grafana URL]
Zipkin: [Zipkin URL]
Eureka: [Eureka URL]

## Health Checks
The health of individual services can be verified using Actuator:

Example: http://localhost:8081/actuator/health

## Future Work
- Complete Prometheus integration.
- Create Grafana Dashboards for system metrics.
- Fully implement the CQRS pattern with the following views:
  - Catalog View
  - Orders/Users View
  - Reviews View
- Add a Shopping Cart Service.
- Add a Payment Service.
- Enhance resilience testing with Resilience4J.
- Load testing with JMeter.
