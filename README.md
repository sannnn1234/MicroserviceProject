# Spring Boot & Microservices Architecture 

## Overview

This project follows a complete microservices architecture using Spring Boot and Spring Cloud. It includes centralized configuration management, service discovery, API gateway routing, inter-service communication, multiple databases, and secure authentication using OAuth2 with Okta.

---

## 1. **Config Server**

### **Description**

The Config Server provides centralized configuration for all microservices. Configuration files are stored in a Git repository and fetched at runtime.

### **Key Responsibilities**

* Centralize configuration for all microservices
* Auto-refresh configuration using Spring Cloud Bus (optional)
* Loads config per environment (dev, qa, prod)

### **Dependencies**

* Spring Boot
* Spring Cloud Config Server
* Git repo for configuration

### **Main Application Properties**

* Enables Config Server using `@EnableConfigServer`
* Points to remote Git repository

---

## 2. **Service Registry (Eureka Server)**

### **Description**

Eureka Server acts as a service registry. All microservices register themselves and discover other services from this registry.

### **Key Responsibilities**

* Register microservices
* Provide load-balanced service discovery
* Monitor health & status of each service

### **Dependencies**

* Spring Boot
* Eureka Server

### **Features**

* Fault tolerance
* Auto-detection of down services
* Dynamic scaling support

---

## 3. **API Gateway**

### **Description**

The API Gateway is the main entry point for all client requests. It routes incoming requests to respective microservices.

### **Key Responsibilities**

* Routing requests to microservices
* Authentication & JWT token validation
* Rate limiting, logging, filters (optional)
* Acts as a security layer between client and services

### **Dependencies**

* Spring Boot
* Spring Cloud Gateway
* Eureka Discovery Client

### **Features**

* Load balancing using Eureka
* Pre & Post Filters
* JWT/Okta Authentication

---

## 4. **Microservices**

The project contains three core business microservices:

* **ProductService**
* **OrderService**
* **PaymentService**

### **Common Characteristics**

* Each service is independently deployable
* Each has its own database (multiple datasources supported)
* Communicates using Feign Clients
* Fetches configuration from Config Server
* Registers with Eureka Server
* Secured using OAuth2 & JWT

### **Dependencies**

* Spring Boot
* Eureka Discovery Client
* Feign Client
* Spring Data JPA
* Multiple datasource support
* MySQL/PostgreSQL (as needed)

### **Inter-Service Communication**

All microservices communicate using **Feign Clients**, enabling:

* Declarative REST calls
* Load-balanced requests
* Easy error handling using fallback methods

---

## 5. **Security Implementation (OAuth2 + Okta)**

### **Description**

All services are fully protected using OAuth2 authentication and JWT-based authorization. Okta is used as the identity provider.

### **Key Responsibilities**

* Authentication using OAuth2 (Okta login)
* JWT token validation at the API Gateway
* Role-based access to microservices

### **Security Workflow**

1. User logs in via Okta
2. Okta issues JWT token
3. Client sends JWT with each request
4. API Gateway validates JWT
5. Gateway forwards request to microservices

### **Dependencies**

* Spring Security
* Spring OAuth2 Resource Server
* JWT Token Validator
* Okta OAuth2 SDK

---

## 6. **Overall Architecture Diagram (Description)**

* Client → API Gateway → Microservices (Product/Order/Payment)
* Each microservice → its own Database
* All services → Config Server + Eureka Server
* Security handled via Okta OAuth2 + JWT

---

## 7. **Technology Stack**

* **Java / Spring Boot**
* **Spring Cloud (Config, Gateway, Eureka, Feign)**
* **OAuth2 + Okta Authentication**
* **MySQL / PostgreSQL Databases**
* **Git for config management**
* **Maven/Gradle**

---

## 8. **Project Highlights**

* Centralized configuration
* Load-balanced microservices
* Independent database per service
* Secure end-to-end communication
* Easy scaling with Eureka + Gateway
* Clean architecture structure

---


