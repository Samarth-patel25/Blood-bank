# 🩸 Blood Bank Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green?style=for-the-badge\&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?style=for-the-badge\&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge\&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

### A Secure RESTful API for Blood Bank Operations

Manage donors, blood inventory, hospitals, patients, and blood requests with role-based access control using Spring Boot.

</div>

---

## 📖 Overview

The **Blood Bank Management System** is a backend application built using **Spring Boot 3** that streamlines blood bank operations through a secure REST API.

It enables:

* 🩸 Donor Registration & Management
* 🏥 Hospital Management
* 📦 Blood Inventory Tracking
* 📋 Blood Request Processing
* 👨‍⚕️ Patient Management
* 🔐 Role-Based Authentication & Authorization

---

## 🚀 Features

### 🔐 Security & Authentication

* Spring Security Integration
* HTTP Basic Authentication
* BCrypt Password Encryption
* Role-Based Access Control (RBAC)

### 🩸 Donor Management

* Register Blood Donors
* Update Donor Information
* Validate Age & Contact Information
* Maintain Donor Records

### 🏥 Hospital Management

* Register Hospitals
* Manage Blood Requests
* Track Hospital Information

### 📦 Blood Inventory

* Add Blood Units
* Update Stock Levels
* Monitor Available Blood Groups

### 📋 Blood Request Workflow

* Raise Blood Requests
* Approve / Reject Requests
* Fulfill Approved Requests
* Track Request Status

### 👨‍⚕️ Patient Management

* Store Patient Records
* Link Patients with Hospitals
* Maintain Medical Information

---

## 🏗️ System Architecture

```text
Client (Postman / Frontend)
            │
            ▼
     Spring Boot REST API
            │
 ┌──────────┼──────────┐
 │          │          │
 ▼          ▼          ▼
Security   Services   Controllers
            │
            ▼
       Spring Data JPA
            │
            ▼
          MySQL
```

---

## 🛠️ Tech Stack

| Category   | Technology      |
| ---------- | --------------- |
| Language   | Java 21         |
| Framework  | Spring Boot 3.2 |
| Security   | Spring Security |
| Database   | MySQL           |
| ORM        | Hibernate / JPA |
| Build Tool | Maven           |
| Validation | Bean Validation |
| Utility    | Lombok          |

---

## 📂 Project Structure

```text
Bloodbank_management
│
├── src/main/java/com/bloodbank
│   ├── controller
│   ├── entity
│   ├── repository
│   ├── service
│   ├── security
│   └── BloodBankApplication.java
│
├── src/main/resources
│   └── application.properties
│
└── pom.xml
```

---

## 🔑 User Roles

| Role     | Description                         |
| -------- | ----------------------------------- |
| ADMIN    | Full system access                  |
| STAFF    | Inventory & request management      |
| DONOR    | Donor-related operations            |
| HOSPITAL | Blood requests & patient management |

---

## 📌 API Access Matrix

| Module              | ADMIN | STAFF | DONOR | HOSPITAL |
| ------------------- | ----- | ----- | ----- | -------- |
| Authentication      | ✅     | ✅     | ✅     | ✅        |
| Donor Management    | ✅     | ✅     | ✅     | ❌        |
| Hospital Management | ✅     | ✅     | ❌     | ✅        |
| Inventory           | ✅     | ✅     | ❌     | ❌        |
| Blood Requests      | ✅     | ✅     | ❌     | ✅        |
| Patients            | ✅     | ✅     | ❌     | ✅        |

---

## ⚙️ Installation & Setup

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/Bloodbank_management.git

cd Bloodbank_management
```

### 2️⃣ Configure MySQL

Update:

```properties
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bloodbank_db?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3️⃣ Build & Run

```bash
mvn clean install

mvn spring-boot:run
```

Application will start at:

```text
http://localhost:8080
```

---

## 📡 Important Endpoints

### Authentication

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | /api/auth/register |
| GET    | /api/auth/me       |

### Donors

| Method | Endpoint             |
| ------ | -------------------- |
| POST   | /api/donors/register |
| GET    | /api/donors          |
| GET    | /api/donors/{id}     |
| PUT    | /api/donors/{id}     |
| DELETE | /api/donors/{id}     |

### Inventory

| Method | Endpoint            |
| ------ | ------------------- |
| GET    | /api/inventory      |
| POST   | /api/inventory      |
| PUT    | /api/inventory/{id} |
| DELETE | /api/inventory/{id} |

### Blood Requests

| Method | Endpoint                  |
| ------ | ------------------------- |
| POST   | /api/requests             |
| GET    | /api/requests             |
| PUT    | /api/requests/{id}/status |

---

## 🔄 Blood Request Lifecycle

```text
PENDING
   │
   ├──► APPROVED ───► FULFILLED
   │
   └──► REJECTED
```

---

## 🔐 Authentication Example

```bash
curl -u username:password http://localhost:8080/api/donors
```

---

## 🧪 Sample User Registration

```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{
  "username":"john_donor",
  "password":"password123",
  "role":"DONOR"
}'
```

---

## 📸 Screenshots

Add screenshots here:

```text
docs/images/
├── login.png
├── donors.png
├── inventory.png
├── requests.png
```

Example:

```md
![Dashboard](docs/images/dashboard.png)
```

---

## 🔮 Future Improvements

* JWT Authentication
* Email Notifications
* Blood Donation Scheduling
* Dashboard Analytics
* Docker Deployment
* Swagger/OpenAPI Documentation
* Unit & Integration Testing

---

## 👨‍💻 Author

**Samarth Patel**

B.Tech Computer Science Engineering Student

GitHub: https://github.com/Samarth-patel25

---

## ⭐ Support

If you found this project useful:

⭐ Star this repository

🍴 Fork the project

🤝 Contribute to make it better

---

## 📜 License

This project is licensed under the MIT License.
