![Java CI](https://github.com/Wadan3/Library-Management-System/actions/workflows/ant.yml/badge.svg)
# 📚 Library Management System

![Java](https://img.shields.io/badge/Java-17-orange)
![OOP](https://img.shields.io/badge/OOP-Object%20Oriented-blue)
![MIT License](https://img.shields.io/badge/License-MIT-green)
![Platform](https://img.shields.io/badge/Platform-Console-lightgrey)

A robust Java-based Library Management System developed using Object-Oriented Programming principles. The application allows librarians to manage books, members, borrowing operations, and returns through an interactive console interface.

---

## 📖 Overview

This project simulates a real-world library management environment and demonstrates the practical implementation of core Java programming concepts.

The system manages:

* Books
* Library Members
* Borrowing Operations
* Return Operations
* Library Information

The project emphasizes clean code, object-oriented design, exception handling, and input validation.

---

## ✨ Features

### 📚 Book Management

* Add books to the library
* View all books
* Search books by ID
* Check book availability

### 👤 Member Management

* Add new members
* View registered members
* Track borrowed books

### 🔄 Borrowing System

* Borrow books
* Return books
* Borrowing limit enforcement
* Availability checking

### 🛡 Validation & Error Handling

* Book ID validation
* Member ID validation
* Borrow limit control
* Exception handling
* User-friendly error messages

---

## 🛠 Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Java Collections Framework
* Exception Handling
* Console-Based User Interface

---

## 🏗 Project Architecture

```text
Library Management System
│
├── Book
│   ├── Book Information
│   ├── Availability Status
│   └── Borrow / Return Methods
│
├── Member
│   ├── Member Information
│   ├── Borrowed Books List
│   └── Borrow / Return Operations
│
├── Library
│   ├── Book Management
│   ├── Member Management
│   ├── Search Operations
│   └── Library Reports
│
└── Lib
    └── Main Application
```

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/Wadan3/Library-Management-System.git
```

### Compile

```bash
javac Lib.java
```

### Run

```bash
java Lib
```

---

## 📋 Sample Menu

```text
========================================
       Library Management System
========================================

1. View all books
2. View all members
3. Add a new book
4. Add a new member
5. Search book by ID
6. Borrow a book
7. Return a book
8. Exit
```

---

## 🎯 Learning Objectives

This project demonstrates:

* Object-Oriented Design
* Class Relationships
* Data Encapsulation
* Exception Handling
* Collections Framework
* Interactive Console Applications
* Software Architecture Fundamentals

---

## 🔒 Validation Rules

| Item         | Rule                   |
| ------------ | ---------------------- |
| Book ID      | Exactly 5 Characters   |
| Member ID    | Exactly 6 Characters   |
| Borrow Limit | Maximum 3 Books        |
| Availability | Book Must Be Available |

---

## 📦 Release

Current Version:

```text
v1.0.0
```

---

## 📜 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

**Abdul Mosawer Wadan**

Computer Engineering Student

Amirkabir University of Technology (Tehran Polytechnic)

GitHub: https://github.com/Wadan3

---

⭐ If you found this project useful, consider giving it a star.
