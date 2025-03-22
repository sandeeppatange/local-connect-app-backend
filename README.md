# **🚀 Local Connect App - Backend**

A **Spring Boot + PostgreSQL** backend for **Local Connect App**, handling bus schedules, routes, and stops.

## **📂 Project Structure**

```
📦 local-connect-app-backend
 ┣ 📂 bus-search-app    # Backend for Bus Timing Service
 ┃ ┣ 📂 src/main/java/com/example/busapp
 ┃ ┃ ┣ 📂 controller      # REST API Controllers
 ┃ ┃ ┣ 📂 model           # Entity Classes (JPA)
 ┃ ┃ ┣ 📂 repository      # JPA Repositories
 ┃ ┃ ┣ 📂 service         # Business Logic (Optional)
 ┃ ┃ ┣ 📜 BusTimingApp.java  # Main Spring Boot Application
 ┃ ┣ 📂 src/main/resources
 ┃ ┃ ┣ 📜 application.properties  # Database Configuration
 ┃ ┃ ┣ 📂 db/migration            # Flyway SQL Scripts (Optional)
 ┃ ┣ 📜 pom.xml              # Project Dependencies
 ┃ ┣ 📜 README.md            # Project Documentation
 ┣ 📜 pom.xml               # Parent Project Config
 ┣ 📜 README.md             # Parent Project Documentation
```

---

## **📦 Bus Search App - Overview**

The **Bus Search App** is a microservice under **Local Connect App** that provides:

- **Bus Schedules**: Search buses based on source & destination.
- **Routes & Stops**: Fetch route details with intermediate stops.
- **REST API**: Exposes endpoints to fetch buses, routes, and schedules.

---

## **📦 Tech Stack**

- **Java 17**
- **Spring Boot 3** (Spring Web, Spring Data JPA)
- **PostgreSQL** (H2 for testing)
- **Flyway (Optional)** - For database migrations
- **Maven** - Dependency management

---

## **🔧 Setup & Installation**

### **1️⃣ Clone the Repository**

```sh
git clone https://github.com/sandeeppatange/local-connect-app-backend.git
cd local-connect-app/bus-search-app
```

### **2️⃣ Configure PostgreSQL**

Ensure PostgreSQL is installed and create a database:

```sql
CREATE DATABASE bus_db;
```

### **3️⃣ Update `application.properties`**

Modify `bus-timing-app/src/main/resources/application.properties`:

```properties
# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/bus_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
```

### **4️⃣ Build & Run the Application**

```sh
mvn clean install
mvn spring-boot:run
```

---

## **🛠 API Endpoints**

| Method  | Endpoint                                       | Description                         |
| ------- | ---------------------------------------------- | ----------------------------------- |
| **GET** | `/buses`                                       | Get all buses                       |
| **GET** | `/routes`                                      | Get all routes                      |
| **GET** | `/schedules/search?sourceId=X&destinationId=Y` | Find buses for source & destination |
| **GET** | `/route-stops/{routeId}`                       | Get stops for a route               |

### **Example: Fetch Route Stops for Route ID 1**

```sh
curl -X GET "http://localhost:8080/route-stops/1"
```

📌 **Response**

```json
[
  {
    "id": 1,
    "stop": { "id": 3, "name": "Main Street" },
    "stopSequence": 1,
    "stopTime": "08:15"
  },
  {
    "id": 2,
    "stop": { "id": 4, "name": "Downtown" },
    "stopSequence": 2,
    "stopTime": "08:30"
  }
]
```

---

## **📜 Database Schema**

```
🚌 Bus
- id (PK)
- bus_number (Unique)

📍 Location
- id (PK)
- name (Unique)

🛣 Route
- id (PK)
- source_id (FK -> Location)
- destination_id (FK -> Location)

🚏 Route_Stop
- id (PK)
- route_id (FK -> Route)
- stop_id (FK -> Location)
- stop_sequence (Integer)
- stop_time (Time)

⏰ Schedule
- id (PK)
- bus_id (FK -> Bus)
- route_id (FK -> Route)
- departure_time (Time)
- arrival_time (Time)
- week_days (VARCHAR)
```

---

## **🐳 Docker Setup (Optional)**

You can run the app with **Docker** using:

```sh
docker-compose up -d
```

📌 **`docker-compose.yml`**

```yaml
version: "3.8"
services:
  postgres:
    image: postgres
    container_name: bus_db
    restart: always
    environment:
      POSTGRES_DB: bus_db
      POSTGRES_USER: your_username
      POSTGRES_PASSWORD: your_password
    ports:
      - "5432:5432"
```

---

## **📌 Future Enhancements**

- **JWT Authentication** for secure API access
- **Redis Caching** for faster schedule lookups
- **Deploy to AWS/GCP** with PostgreSQL

---

## **📜 License**

This project is licensed under the **MIT License**.

---
