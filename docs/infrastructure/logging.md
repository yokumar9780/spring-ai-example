# Logging

This document describes the logging setup for the Spring AI Example project.

## Overview

Logging is configured using Logback to capture application events and errors.

## Configuration

1. Logback Configuration:

   - The configuration file is located at `src/main/resources/logback-spring.xml`.
   - Logs are stored in the `logs` directory under the project root.

2. Log Levels:

   - Default log levels are defined in `application.yml`:
     ```yaml
     logging:
       level:
         org.springframework: info
         com.example: debug
     ```

3. Log Rotation:
   - Logs are rotated daily and compressed using the `SizeAndTimeBasedRollingPolicy` in `logback-spring.xml`.

## TODO

- Add instructions for integrating with centralized logging systems like ELK or Loki.
