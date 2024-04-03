#FROM openjdk:23-
#
#ARG JAR_FILE=target/*.jar
#
#COPY ./target/backend-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Use an official Maven image as the base image
FROM maven:3.9.6-eclipse-temurin-11-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use an official OpenJDK image as the base image
FROM openjdk:23-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar .

# Set the command to run the application
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]