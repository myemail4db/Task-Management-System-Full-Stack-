# Day 1 - Spring Boot Crash Study Plan


## Morning (Concepts & Setup)

- Learn the basics: Learn the basics: Spring vs Spring Boot, auto-configuration, embedded Tomcat.

- Difference between Spring and Spring Boot.

- Key annotations: $@SpringBootApplication$, $@RestController$, $@Service$, $@Repository$, $@Autowired$, $@Configuration$.

- Dependency Injection and the MVC flow.


## Afternoon (Hands-On Practice)

### 1. Build a Simple Project

- Use Spring Initializr

- Add **Spring Web**, **Spring Data JPA**, **H2** (in-memory DB).

- Build a simple Task API:

    - $Task$ entity (id, name, status).

    - $TaskRepository$ extending $JpaRepository$.

    - $TaskService$ with CRUD methods.

    - $taskController$ exposing endpoints ($/tasks$).

- Test endpoints using Postman or curl.


## Evening (Interview Readiness)

- Review **common Spring Boot interview questions**:

    - Difference between Spring vs Spring Boot.

    - What does $application.properties$ do?

    - How does dependency injection work?

    - What’s the difference between $@Component$, $@Service$, and $@Repository$?

    - Be ready to **explain your project**: how request → controller → service → repository → database works.

