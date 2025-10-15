# Task Management System (Full Stack)

This is a **full-stack project** built for practice and interview preparation.  
It includes a **Spring Boot + MySQL backend** and a **React + AngularJS frontend demo**.  
The system allows users to manage tasks, projects, users, and labels.

---

## 🚀 Features
- Backend (Spring Boot)
  - REST API with CRUD operations for tasks, projects, and users
  - Update task status (`OPEN`, `IN_PROGRESS`, `DONE`)
  - MySQL database integration
  - Logging with correlation IDs for troubleshooting
- Frontend (React + AngularJS demo)
  - Task list UI connected to backend API
  - Update task status from the UI
  - AngularJS demo with `ng-model`, `ng-repeat`, `ng-if`
  - React demo with `useState`, `useEffect`, Axios

---

## ⚙️ Tech Stack
- **Backend**: Java 17, Spring Boot 3.x, Spring Data JPA, MySQL 8.x
- **Frontend**: React 18, AngularJS 1.8, Axios, HTML/CSS
- **Build Tools**: Maven, npm
- **Database**: MySQL (schema: `JavaSpringBootTaskdb`)

---

## 📂 Project Structure

```bash
/ (root)
├── backend/ # Spring Boot API
│ ├── src/main/java/com/example/taskapi/
│ └── src/main/resources/
│
├── frontend/ # React + AngularJS demo
│ ├── src/
│ ├── public/
│ └── angularjs-demo.html
│
└── README.md # (this file)
```

---

## 🗂 Setup Instructions

### 1. Backend Setup (Spring Boot + MySQL)
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Make sure MySQL is running and the database `JavaSpringBootTaskdb` is created.

### 2. Frontend Setup (React + AngularJS)

```bash
cd frontend
npm install
npm start
```

Runs at http://localhost:3000

Backend runs at http://localhost:8080

### 3. AngularJS Demo

Open directly:

```bash
frontend/src/angularjs-demo.html
```

--- 

## 🔗 API Examples

```bash
# List tasks
curl -i "http://localhost:8080/api/tasks?size=2"

# Update task status
curl -i -X PATCH "http://localhost:8080/api/tasks/123/status?status=IN_PROGRESS" \
  -H "X-Correlation-Id: demo-1"
```

---

📖 Troubleshooting

CORS issues → add proxy in `frontend/package.json`:

```json
"proxy": "http://localhost:8080"
```

Database errors → ensure correct table names match entities (`@Table(name="tasks")`).

React errors → check missing files (`index.js`, `UpdateStatus.jsx`).

---

## 📝 Notes

- This project is designed for hands-on learning of:

    - Spring Boot REST APIs

    - Database integration with MySQL

    - React state & hooks (useState, useEffect)

    - AngularJS directives (ng-model, ng-repeat, ng-if)

- It’s a good reference for **skills assessments**, **coding interviews**, **and full-stack portfolio projects**.


---

✅ This **root README** points interviewers and recruiters to:  
- Project purpose  
- Tech stack  
- Backend & frontend setup  
- API examples  
- Troubleshooting  
