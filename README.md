# 🏨 Reactive Hotel API

A **reactive REST API** built using **Spring WebFlux**, powered by **Java 21** and **Gradle**. This project demonstrates building non-blocking services using `Mono` and `Flux` from **Project Reactor**.

---

## 📌 Endpoints

### ➤ Get All Hotels

- **Method**: `GET`
- **URL**: `http://localhost:8080/api/reactive-hotels`
- **Response**: Returns a list of all hotels as a reactive stream (`Flux<Hotel>`)

#### ✅ Sample Response

```json
[
  {
    "id": 1,
    "name": "Hotel California",
    "latitude": 34.05,
    "longitude": -118.25
  },
  {
    "id": 2,
    "name": "The Grand Budapest",
    "latitude": 47.0,
    "longitude": 19.0
  }
]
````

### ➤ Get Hotel by ID

- **Method**: `GET`
- **URL**: `http://localhost:8080/api/reactive-hotels/{id}`
- **Response**: Returns the hotel with the specified ID as a single object (`Mono<Hotel>`)

#### ✅ Sample Response

```json
{
  "id": 1,
  "name": "Hotel California",
  "latitude": 34.05,
  "longitude": -118.25
}
```

## 🔧 Technologies Used

- Java 21
- Spring Boot 3+
- Spring WebFlux
- Gradle
- Project Reactor
- JUnit 5
- StepVerifier
- WebTestClient

---

## 🚀 Getting Started

### 🔹 Build the Project

```bash
./gradlew bootRun
```

## 🧪 Run Tests

```bash
./gradlew test
```

## 📁 Project Structure

```bash
src/
├── main/
│   └── java/
│       └── com/testing/webflux/
│           ├── controller/
│           ├── entity/
│           └── repository/
└── test/
    └── java/
        └── com/testing/webflux/controller/
```

## 🛠️ Future Enhancements

- Add Create/Update/Delete endpoints
- Connect to a reactive database (e.g., R2DBC, MongoDB)
- Add Swagger/OpenAPI documentation
- Add Dockerfile and docker-compose support

## 🤝 Contributions

We welcome contributions to enhance this project!