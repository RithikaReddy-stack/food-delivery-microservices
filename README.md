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

\`\`\`
[ Client (Angular) ]
        |
        v
[ API Gateway (8080) ]
        |
        +--> [ User Service (8081) ]
        +--> [ Restaurant Service (8082) ]
        +--> [ Order Service (8083) ]
        +--> [ Delivery Service (8084) ]
        +--> [ Notification Service (8085) ]
                  ^
                  |
            [ Kafka Broker ]
\`\`\`

---

## 🚀 How to Run

### ✅ Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose
- Kafka & Zookeeper (local or containerized)
- MySQL / MongoDB

### 🔧 Run Without Docker

1. Start MySQL and MongoDB
2. Start Zookeeper and Kafka
3. Start Eureka server:  
   \`cd eureka-server && mvn spring-boot:run\`
4. Start API Gateway:  
   \`cd api-gateway && mvn spring-boot:run\`
5. Start remaining services (user, order, etc.)

---

### 🐳 Run with Docker (Coming Soon)

\`\`\`bash
docker-compose up --build
\`\`\`

> Dockerfile and docker-compose.yml are being prepared.

---

## 🔐 Authentication

- JWT token is used for user login and API access
- Login endpoint returns JWT in response
- Use Authorization: Bearer \`<token>\` in API headers

---

## 📫 Kafka Topics (Example)

| Topic Name         | Producer         | Consumer             |
|--------------------|------------------|----------------------|
| \`order_placed\`     | Order Service     | Notification Service |
| \`delivery_assigned\`| Delivery Service  | Notification Service |

---

## 📘 API Documentation

Swagger available (if enabled) at:

\`\`\`
http://localhost:8081/swagger-ui/
http://localhost:8082/swagger-ui/
...
\`\`\`

---

## ✍️ Author

👩‍💻 **Rithika Reddy**  
[GitHub](https://github.com/RithikaReddy-stack)

---

## 📌 Future Enhancements

- Payment Gateway Integration
- Redis Caching
- Kubernetes Deployment (K8s)
- Centralized Logging with ELK

