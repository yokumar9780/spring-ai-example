# Monitoring

This document outlines the monitoring setup for the Spring AI Example project.

## Overview

Monitoring is essential to ensure the application is running smoothly and to identify any issues proactively.

## Tools and Technologies

- **Spring Boot Actuator**: Provides built-in endpoints for monitoring and management.
- **Custom Logs**: Configured using `logback-spring.xml`.

## Configuration

1. Enable Actuator endpoints in `application.yml`:

   ```yaml
   management:
     endpoints:
       web:
         exposure:
           include: '*'
   ```

2. Access Actuator endpoints:

   - Example: `http://localhost:8080/actuator/health`

3. Log Configuration:
   - Logs are configured in `logback-spring.xml`.
   - Logs are stored in the `logs` directory.

## TODO

- Add integration with external monitoring tools like Prometheus or Grafana.
- Document any custom metrics or health checks.
