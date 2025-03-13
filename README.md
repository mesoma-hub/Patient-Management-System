# Patient Service API

## 📌 Overview
This is a **Spring Boot** RESTful microservice API for managing patient records. It provides endpoints for creating, retrieving, updating, and deleting patients. The application is containerized using **Docker** and built with **Maven**.

## 🚀 Features
- Create a new patient
- Retrieve all patients
- Update patient details
- Delete a patient
- API documentation using **Swagger/OpenAPI**

---

## 🛠️ Technology Stack
- **Java 21**
- **Spring Boot**
- **Maven**
- **Docker**
- **Spring Validation**
- **OpenAPI (Swagger)**

---

## 🏗️ Project Setup
### 1️⃣ Clone the Repository
```sh
git clone https://github.com/mesoma-hub/Patient-Management-System.git
cd patient-service
```

### 2️⃣ Build the Project
```sh
mvn clean package
```

### 3️⃣ Run the Application
```sh
mvn spring-boot:run
```

Application runs on **http://localhost:4000**

---

## 🐳 Running with Docker
### 1️⃣ Build the Docker Image
```sh
docker build -t patient-service .
```

### 2️⃣ Run the Container
```sh
docker run -p 4000:4000 patient-service
```

---

## 📌 API Endpoints
| Method | Endpoint | Description |
|--------|----------------|-----------------------------|
| GET | `/patients` | Get all patients |
| POST | `/patients` | Create a new patient |
| PUT | `/patients/{id}` | Update patient details |
| DELETE | `/patients/{id}` | Delete a patient |

---

## 📜 Swagger API Documentation
After running the application, visit:
**`http://localhost:4000/swagger-ui.html`**

---

## 🤝 Contributing
Feel free to fork the repository and submit **pull requests**.

---

## 📄 License
This project is licensed under the **MIT License**.

---

### ✨ Author
- **Mesoma**
- **anibgogumesoma1@gmail.com**
- **mesoma-hub**

