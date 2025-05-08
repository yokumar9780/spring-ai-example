# Local Development Setup

This document provides instructions for setting up the local development environment for the Spring AI Example project.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 21 is installed and configured.
- **Maven**: Use the Maven wrapper (`mvnw` or `mvnw.cmd`) provided in the project.
- **Docker**: Install Docker to run the PostgreSQL database with pgvector.

## Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/yokumar9780/spring-ai-example.git
   cd spring-ai-example
   ```

2. Start the PostgreSQL database using Docker Compose:

   ```bash
   cd dev/docker-compose
   docker-compose up -d
   ```

3. Configure the application:

   - Update `src/main/resources/application-dev.yaml` with your proxy and OpenWeather API details if required.

4. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

5. Access the application:
   - The application will be available at `http://localhost:8080`.

## Notes

- The database schema and initial data are automatically set up using the `init.sql` script.
- Logs are stored in the `logs` directory under the project root.

## TODO

- Add instructions for running tests.
- Document any additional environment variables required.
