# 📘 Javalin Blog API

---

## 🧾 Overview

Javalin Blog API is a **RESTful backend service** built using **Java** and the **Javalin** web framework. It serves as the backend for a simple micro-blogging platform where users can register, log in, post messages, view all posts, and manage their own content.

This application demonstrates **CRUD operations**, **REST API principles**, and a **multi-layered architecture** (Controller, Service, DAO). It was developed as part of a backend-focused project to practice and apply Java web development skills.

---

## ✅ Features

* **User Registration:** `POST /register`
* **User Login:** `POST /login`
* **Post a Message:** `POST /messages`
* **Get All Messages:** `GET /messages`
* **Get Message by ID:** `GET /messages/{message_id}`
* **Get Messages by User:** `GET /accounts/{account_id}/messages`
* **Delete a Message:** `DELETE /messages/{message_id}`
* **Update a Message:** `PATCH /messages/{message_id}`

---

## 🧰 Technologies Used

* **Java 17**
* **Javalin:** Lightweight web framework
* **JDBC:** For database interaction
* **H2 Database:** (or MySQL/your choice during local testing)
* **JUnit:** For integration testing

---

## 🏗️ Project Structure

The project follows a **3-layer architecture**:

* **Controller Layer:** Handles API routes and HTTP requests (via `SocialMediaController`)
* **Service Layer:** Contains business logic and validations
* **DAO Layer:** Manages data access and SQL operations

📌 Database schema and seed script are provided in `src/main/resources`.

---

## 🚀 Getting Started

### Prerequisites

* **Java 17+**
* **Maven** (or your preferred build tool)
* **IDE** like IntelliJ IDEA or VSCode

### Setup Instructions

1.  **Clone the Repository**

    ```bash
    git clone [https://github.com/your-username/javalin-blog-api.git](https://github.com/your-username/javalin-blog-api.git)
    cd javalin-blog-api
    ```

2.  **Build the Project**

    ```bash
    mvn clean install
    ```

3.  **Run the Application**

    ```bash
    mvn exec:java -Dexec.mainClass="Main"
    ```

4.  **Access API**

    App runs by default on: `http://localhost:8080/`

    Use **Postman** or `curl` to interact with the API endpoints.

---
## 📦 Usage Examples

---

### ✅ Register a New User

```http
POST /register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "securepass"
}
```

---

### 📝 Post a Message

```http
POST /messages
Content-Type: application/json

{
  "posted_by": 1,
  "message_text": "Hello world!"
}
```

## 👥 Contributors

Azamat Shogen – [your-github-link](https://github.com/Azamat-Shogen)

## 📄 License

This project is licensed under the **MIT License** – see the [`LICENSE`](https://www.google.com/search?q=LICENSE) file for details.

## 📚 Documentation

For detailed architecture and design decisions, see the [project wiki](https://www.google.com/search?q=link-to-your-wiki-if-available).
