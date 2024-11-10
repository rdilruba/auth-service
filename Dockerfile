# Stage 1: Build the applicationFROM openjdk:17-jdk-slim-buster
FROM --platform=linux/amd64 maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM --platform=linux/amd64 openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar /app/auth-service.jar

# Set the command to run the application
CMD ["java", "-jar", "auth-service.jar"]

# Expose the port on which the app runs
EXPOSE 8080
