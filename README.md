#Spring boot and Microservice
<br>
1. Config Server
This module contains the configuration server which centralizes the configuration for all microservices.

Dependencies: Spring Cloud Config Server, Spring Boot, Config from Git Repo.
2. Service Registry (Eureka Server)
This module acts as a service registry where all the microservices will register themselves.

Dependencies: Eureka Server, Spring Boot.
3. API Gateway
This module acts as the gateway for all client requests. It will route requests to the appropriate microservices.

Dependencies: Spring Cloud Gateway, Eureka Discovery Client, Spring Boot.
4. Service ProductService, OrderService, PaymentService (Microservices)
These are the microservices which contain business logic and communicate with each other through Feign clients.

Dependencies: Spring Boot, Eureka Discovery Client, Feign Client, Multiple DataSources (for multiple databases).
5. Security
All services are secured using OAuth2 with Okta as the authentication provider. JWT tokens are used for security.

