# 🎓 Student Management System (Spring Boot)

A fully functional **RESTful CRUD API** built with **Spring Boot**, designed to manage student records efficiently.  
This project demonstrates core backend development skills — from building clean APIs to managing database persistence — using modern Spring Boot best practices.

---

## 🚀 Features

✅ Create, Read, Update, and Delete (CRUD) student records  
✅ Partial updates supported via `PATCH`  
✅ Exception handling with meaningful HTTP responses  
✅ Integrated with **Spring Data JPA** for data persistence  
✅ Database configuration via `application.properties`  
✅ Structured with a clear **Service → Repository → Controller** architecture  
✅ Tested endpoints using **Postman**  
✅ Ready for integration with a frontend or other microservices  

---

## 🧠 Tech Stack

| Layer | Technology Used |
|-------|------------------|
| Backend Framework | Spring Boot |
| Language | Java |
| Database | MySQL / H2 (for testing) |
| ORM | Spring Data JPA |
| Build Tool | Maven |
| IDE | IntelliJ IDEA |

---

## 🧩 Project Architecture

```plaintext
com.rapheal.student_management_system
├── Entities
│   └── Student.java
├── repositories
│   └── StudentRepository.java
├── services
│   ├── StudentService.java
│   └── StudentServiceImpl.java
├── controllers
│   └── StudentController.java
└── StudentManagementSystemApplication.java

