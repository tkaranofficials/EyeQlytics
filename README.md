# Country API - Spring Boot Application

## 📌 Project Overview
This Spring Boot application provides CRUD operations for country data and integrates with the REST Countries API to fetch and store country details. The application also supports authentication with JWT, Docker integration, and API documentation via Swagger.

## 🚀 Features
- Fetch country data from an external API.
- CRUD operations for country data.
- JWT authentication with Spring Security.
- Dockerized setup with MySQL.
- Swagger API documentation.
- Postman collection for easy testing.

## 🛠️ Tech Stack
- **Backend:** Java, Spring Boot, Spring Security, JWT
- **Database:** MySQL
- **Containerization:** Docker, Docker Compose
- **API Documentation:** Swagger, Postman

## 📥 Installation & Setup

### 1️⃣ Clone the Repository
```sh
 git clone https://github.com/your-username/your-repository.git
 cd your-repository
```

### 2️⃣ Configure Environment Variables
- Update `application.properties` for database configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/country_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 3️⃣ Run the Application
#### **Without Docker**
```sh
mvn spring-boot:run
```

#### **With Docker**
```sh
docker-compose up -d
```

## 📝 API Endpoints
| Method | Endpoint          | Description                    |
|--------|------------------|--------------------------------|
| GET    | `/countries`      | Retrieve all countries        |
| GET    | `/countries/{id}` | Get a country by ID           |
| POST   | `/countries`      | Add a new country manually    |
| PUT    | `/countries/{id}` | Update country details        |
| DELETE | `/countries/{id}` | Delete a country by ID        |
| POST   | `/countries/fetch` | Fetch & store data from API  |

## 🔐 JWT Authentication
- **Login:** `POST /auth/login`
- **Register:** `POST /auth/register`
- Include the JWT token in the `Authorization` header: `Bearer <token>`

## 🏗️ Docker Commands
### **Build and Run Docker Container**
```sh
docker build -t spring-boot-country-api .
docker run -p 8080:8080 spring-boot-country-api
```

### **Using Docker Compose**
```sh
docker-compose up -d
```

## 📜 Swagger API Documentation
Once the application is running, access Swagger UI:
```
http://localhost:8080/swagger-ui/index.html
```

## 🔬 Postman Collection
Import the provided Postman collection to test the APIs.
- File: [`EyeQlytics.postman_collection`](EyeQlytics.postman_collection.json)
- Steps:
  - Open **Postman**.
  - Click on **Import** > **Upload File**.
  - Select `CountryAPI.postman_collection.json`.
  - Start testing!


## 📝 Contribution
Feel free to fork the repo and submit pull requests!

---
📧 **Contact:** tkaranofficials@gmail.com

