# Spring Boot Application

## Technical Stack

- **Spring Boot**: Version 3.4.3
- **Java**: Version 17
- **Thymeleaf**: Template engine for dynamic web page rendering
- **Bootstrap**: Version 4.3.1 for responsive and modern design
- **Database**: MySQL (or other DBMS, depending on your configuration)

## Requirements

Before you begin, make sure you have the following tools installed on your machine:

- **Java 17**: Ensure that Java 17 is installed by running:
    ```bash
    java -version
    ```
- **Maven**: Used for dependency management and building the project.
    You can check if Maven is installed with:
    ```bash
    mvn -v
    ```
- **MySQL**: Make sure you have a running database server (MySQL).

## Setup in Production

1. **Run the SQL script** to create the `demo` database and necessary tables:

2. **Configure environment variables** for production (example for MySQL and OAuth2 GitHub):
    - **Database**:
      ```properties
      spring.datasource.username=${DB_USERNAME}
      spring.datasource.password=${DB_PASSWORD}
      ```
    - **OAuth2 (GitHub)**:
      ```properties
      spring.security.oauth2.client.registration.github.client-id=${GITHUB_CLIENT_ID}
      spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CLIENT_SECRET}
      ```

    You need to define these environment variables before starting the application.

3. **Run the application in production mode**:
    Use Maven to compile and start the application with the `prod` profile:
    ```bash
    mvn spring-boot:run -Dspring.profiles.active=prod
    ```
    Or
    ```bash
     java -Dspring.profiles.active=prod -jar spring-boot-skeleton.jar
    ```
    The application will be available at `http://localhost:5001`.

## Setup in Local

1. **Run the application in local mode**:
    To start the application with the `local` profile, use the following command:
    ```bash
    mvn spring-boot:run -Dspring.profiles.active=local
    ```
    Or
    ```bash
     java -Dspring.profiles.active=local -jar spring-boot-skeleton.jar
    ```
    The application will be available at `http://localhost:5001`.
