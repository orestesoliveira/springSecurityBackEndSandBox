# orestes-oliveira-java-be

This project contains the backend implementation for the Orestes Oliveira platform.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Tests](#tests)

## Installation

   1. Clone the repository:

   ```sh
   git clone https://github.com/reigncl/orestes-oliveira-java-be.git
   ```
   
    ssh = git@github.com:reigncl/orestes-oliveira-java-be.git



## Usage
   This project provides backend services for the Orestes Oliveira platform. It includes features such as article management, user authentication, and data processing.
   
   To use the project:
   Ensure all dependencies are installed (see Installation).
   
   run docker compose


   Start the application:
   ```sh
   # If using Maven
   mvn spring-boot:run
   # If using Gradle wrapper
   ./gradlew bootRun
   ```
   
   postgres database credentials
   username : orestes_be
   password : orestes_be_pass
   
   manually insert user and encrypted password into postgres users table
   ```sql
   INSERT INTO USERS (username, password) VALUES ('username', '$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy');
   ```
   
   Access the provided endpoints using an API client or web browser.

   # Api credentials
       user : username
       password : password
   
   # Endpoints
       
   http://localhost:8080/articles?page=0&size=5


## Tests

   To run the tests, use the following command:
   
   ```sh
   # If using Maven
   mvn test
   ```
