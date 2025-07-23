# 🍔 Food Delivery Microservices System

A microservices-based food delivery platform built with **Spring Boot**, **Spring Cloud**, **Eureka**, **Kafka**, **JWT Authentication**, and **Docker**. 
This system simulates a real-world scalable application involving users, restaurants, orders, delivery, and notifications.

---

## 📦 Microservices Breakdown

| Service              | Port | Description                                |
|----------------------|------|--------------------------------------------|
| **Eureka Server**     | 8761 | Service registry for service discovery     |
| **API Gateway**       | 8080 | Routes external requests to microservices  |
| **User Service**      | 8081 | Handles registration, login, profiles (JWT)|
| **Restaurant Service**| 8082 | Restaurant listing & menu management       |
| **Order Service**     | 8083 | Order placement, tracking, payment         |
| **Delivery Service**  | 8084 | Assigns and tracks delivery personnel      |
| **Notification Service** | 8085 | Listens to Kafka topics for email/SMS     |

---

## 🧰 Tech Stack

- **Java 17**, **Spring Boot**, **Spring Security**, **Spring Cloud**
- **Eureka Service Discovery**
- **API Gateway (Spring Cloud Gateway / Zuul)**
- **JWT Token-based Authentication**
- **Apache Kafka** for async communication
- **MySQL**, **MongoDB** for data persistence
- **Docker & Docker Compose** for containerization
- **Angular** (frontend - separate repo/module)
- **Swagger** (for API documentation)

---

## 🗺️ Architecture Diagram (Conceptual)


