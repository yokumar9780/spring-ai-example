# Deployment

This document provides guidelines for deploying the Spring AI Example project.

## Overview

The application is designed to run as a Spring Boot application with PostgreSQL as the database.

## Deployment Steps

1. Build the application:
   ```bash
   ./mvnw clean package
   ```

2. Run the application:
   ```bash
   java -jar target/chatbot-0.0.1-SNAPSHOT.jar
   ```

3. Database Setup:
   - Ensure the PostgreSQL database is running.
   - Use the `init.sql` script to initialize the database schema and data.

4. Environment Variables:
   - Configure the following variables as needed:
     - `OPEN_WEATHER_URL`
     - `OPEN_WEATHER_API_KEY`

## TODO

- Add instructions for deploying to cloud platforms like AWS or Azure.
- Document CI/CD pipeline setup.