# --- Stage 1: Build the Spring Boot application ---
# Use a Gradle image for compiling the application
FROM gradle:8-jdk21-jammy AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper, settings, and build files to leverage Docker's cache
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the rest of the application source code
COPY src src

# Build the Spring Boot application, skipping tests to save time and copying dependencies to separate layer for caching
RUN ./gradlew build --no-daemon -x test

# --- Stage 2: Create the final, lightweight runtime image ---
# Use a minimal JRE base image for the runtime
FROM eclipse-temurin:21-jre-jammy

# Add a non-root user and group for security
RUN groupadd --system springboot && useradd --system --gid springboot springboot
USER springboot

# Expose the port your application will run on (default is 8080)
EXPOSE 8080

# Tune the JVM for container memory limits
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=80.0"

# Add a health check to ensure the application is truly ready
HEALTHCHECK --interval=30s --timeout=10s --retries=3 --start-period=60s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Copy the built JAR from the 'builder' stage into the final image
COPY --from=builder /app/build/libs/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]