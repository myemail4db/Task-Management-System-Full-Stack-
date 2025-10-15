# Task Management API (Spring Boot + MySQL)

This is a **Spring Boot REST API** for managing tasks, projects, users, and labels.  
It follows a layered architecture (Controller → Service → Repository → Database) and includes logging with correlation IDs for easier troubleshooting.

---

## 🚀 Features
- CRUD operations for Tasks, Projects, and Users
- Search and filter tasks with pagination
- Update task status (`OPEN`, `IN_PROGRESS`, `DONE`)
- MySQL database integration
- Structured logging with Correlation IDs
- Spring Data JPA for repository layer

---

## ⚙️ Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL 8.x
- Maven

---

## 🗂 Project Structure

```bash
src/
├── main/java/com/example/taskapi/
│ ├── domain/               # Entities (Task, Project, User, etc.)
│ ├── repository/           # Repositories (Spring Data JPA)
│ ├── service/              # Business logic
│ ├── web/                  # REST Controllers
│ └── log/                  # Correlation ID logging filter
└── resources/
└── application.properties
```

---

## 📦 Setup

### 1. Database

```sql
CREATE DATABASE JavaSpringBootTaskdb;
```

#### Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/JavaSpringBootTaskdb
spring.datasource.username=UserTaskdb
spring.datasource.password=YourPassword
spring.jpa.hibernate.ddl-auto=update
```

### 2. Run Application

```bash
cd task-api
mvn spring-boot:run
```

## 🔗 API Endpoints

### Tasks

- `GET /api/tasks` → List tasks (with pagination)

- `GET /api/tasks/{id}` → Get task by ID

- `PATCH /api/tasks/{id}/status?status=IN_PROGRESS` → Update task status

### Projects

- `GET /api/projects`

- `POST /api/projects`

## 🧪 Testing with curl

```bash
# Get first 2 tasks
curl -i "http://localhost:8080/api/tasks?size=2"

# Update task status
curl -i -X PATCH "http://localhost:8080/api/tasks/123/status?status=IN_PROGRESS" \
  -H "X-Correlation-Id: demo-1"
```

## 📖 Troubleshooting

- 500 error on tasks → check table name (`@Table(name="tasks")` must match database).

- Patch not working → ensure `@PatchMapping` is used, not `@GetMapping`.

- CORS errors → allow from frontend origin or use proxy in frontend.

--- 

## Here are some sample tests to make sure the application is working correctly


### Quick tests:

```bash
# list tasks (paged)
curl "http://localhost:8080/api/tasks?size=5"

# Result
{"correlationId":"7afed198-59f8-433e-8edf-6cb85d2f5fea","message":"JDBC exception executing SQL [select t1_0.task_id,t1_0.created_at,t1_0.creator_user_id,t1_0.description,t1_0.due_date,t1_0.estimated_points,t1_0.project_id,t1_0.status,t1_0.title,t1_0.updated_at from tasks t1_0 limit ?,?] [Unknown column 't1_0.estimated_points' in 'field list'] [n/a]; SQL [n/a]","error":"InvalidDataAccessResourceUsageException"}



# search
curl "http://localhost:8080/api/tasks?q=feature&size=5"

# Result
{"correlationId":"fe323c2e-3c52-4161-9816-7a535f8a55ce","message":"JDBC exception executing SQL [select t1_0.task_id,t1_0.created_at,t1_0.creator_user_id,t1_0.description,t1_0.due_date,t1_0.estimated_points,t1_0.project_id,t1_0.status,t1_0.title,t1_0.updated_at from tasks t1_0 where upper(t1_0.title) like upper(?) escape '\\\\' limit ?] [Unknown column 't1_0.estimated_points' in 'field list'] [n/a]; SQL [n/a]","error":"InvalidDataAccessResourceUsageException"}



# get by id
curl "http://localhost:8080/api/tasks/123"

# Result
{"correlationId":"2f40a165-0a47-4c7a-81c5-0e3f3cce9556","message":"JDBC exception executing SQL [select t1_0.task_id,t1_0.created_at,t1_0.creator_user_id,t1_0.description,t1_0.due_date,t1_0.estimated_points,t1_0.project_id,t1_0.status,t1_0.title,t1_0.updated_at from tasks t1_0 where t1_0.task_id=?] [Unknown column 't1_0.estimated_points' in 'field list'] [n/a]; SQL [n/a]","error":"InvalidDataAccessResourceUsageException"}



# update status (note correlation id appears in logs/headers)
curl -i -X PATCH "http://localhost:8080/api/tasks/123/status?status=IN_PROGRESS" -H "X-Correlation-Id: demo-1"

# Result
HTTP/1.1 200
X-Correlation-Id: demo-1
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 29 Sep 2025 19:12:18 GMT

{"taskId":123,"projectId":76,"title":"Task 123 Title","description":"Description of task 123.","priority":"HIGH","status":"IN_PROGRESS","estimatePoints":8,"creatorUserId":52,"createdAt":"2024-06-21T17:00:00","updatedAt":"2025-09-29T12:12:18.8104334","dueDate":null}


```

# Medium-size relational dataset
## Tables & sizes

- users (600)

- projects (180)

- tasks (12,000)

- task_assignments (~18,000) — many-to-many

- comments (35,000)

- labels (800)

- task_labels (~24,000) — many-to-many