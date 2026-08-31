# Todo List Spring Boot Application

A simple Todo List web application built with Java, Spring Boot, Maven, and PostgreSQL.

## Features

- User registration and authentication
- Add, edit, and delete tasks

## Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Maven
- PostgreSQL
- HTML, CSS, JavaScript (frontend)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/pthormodsen/TODOListSpringBoot.git
   cd TODOListSpringBoot
   ```

2. **Configure the database:**
    - Create a PostgreSQL database named `tododb`.
    - Update the `.env` file with your database credentials:
      ```
      SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tododb
      SPRING_DATASOURCE_USERNAME=your_db_user
      SPRING_DATASOURCE_PASSWORD=your_db_password
      SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
      ```

3. **Build and run the application:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the app:**
    - Open your browser and go to `http://localhost:8080`

## Development

- The backend code is in `src/main/java/no/patreek/todolistspringboot/`
- The frontend files are in `src/main/resources/static/`

<img width="1493" height="725" alt="Screenshot 2026-08-31 at 22 02 20" src="https://github.com/user-attachments/assets/888783ff-7c90-4906-8bbe-f9fd6c88b6c6" />

<img width="1510" height="738" alt="Screenshot 2026-08-31 at 22 02 11" src="https://github.com/user-attachments/assets/4e887f7d-5628-409d-8119-5565bff3b2d1" />





