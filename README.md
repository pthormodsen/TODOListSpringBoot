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

## License

This project is licensed under the MIT License.
