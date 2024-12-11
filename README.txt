# Backend Project

This is the backend service for the Ticketing System. It handles all business logic related to customer and vendor management, ticket management, and configuration settings.


## Overview

This project implements a Real-Time Event Ticketing System using Spring Boot for the backend and Angular for the frontend. It simulates a ticket sales environment using a Producer-Consumer pattern, where vendors add tickets to a pool, and customers attempt to purchase tickets concurrently. 


### Key Features:
- *Customer Management*: Handles creation, retrieval, and updating of customer information.
- *Producer-Consumer pattern*: to simulate vendor ticket releases and customer purchases
- *Concurrency management*:  Manage through multi-threading and synchronization
- *MySQL*: Integration with MySQL for storing transaction data
- *Thread*: Integration with MySQL for storing transaction data
  
## Prerequisites

Before you begin, ensure you have the following installed:

- Backend: Spring Boot, Java 21
- Frontend: React JS
- Database: MySQL 
- Build Tool: Maven (Backend), Vite (Frontend)
- Git (for cloning the repository)
- Apache Maven (for building the project)
- A running database (if applicable) for data persistence
- An IDE like IntelliJ IDEA, Eclipse, or VS Code for development

## Backend Setup

1. *Clone the repository*:
    bash
    git clone https://github.com/shone26/Ticketing-System.git
    cd Backend
    

2. *Install dependencies*:
    This project uses Maven for dependency management. Run the following command to install all necessary dependencies:

    bash
    mvn install
    

3. *Configure the database* (if applicable):

- Edit Following Application.properties

    spring.datasource.url=jdbc:mysql://localhost:3306/ticketing_system
    spring.datasource.username=root
    spring.datasource.password=yourpassword

    
## Running the Application

You can run the backend service by using Maven:

bash
mvn spring-boot:run

## Frontend Setup

1. *Clone the repository*:
    bash
    git clone https://github.com/shone26/Ticketing-System.git
    cd Frontend

2. *Navigate to the frontend folder:*:
    cd Frontend

3. *Install dependencies*:
    npm install

4. *Run the Angular application*:
    ng serve


## Test the Application

- You can access the frontend in your browser at http://localhost:4200.
- The backend will handle ticket transactions at http://localhost:8080.

## Acknowledgments

- Spring Boot - For building the backend REST API
- React - For creating the frontend application
- MySQL - For data storage
- Vite - For fast frontend build tool
- Tailwind CSS - For utility-first CSS framework
- ShadCNUI - For component library to design UIs efficiently



```

