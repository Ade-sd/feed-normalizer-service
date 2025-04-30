# Feed Normalizer Service

The Feed Normalizer Service is a Spring Boot-based microservice designed for data normalization.

## Running the Application

You can start the application using one of the following methods:

### 1. Using Spring Boot (Manually)
Run the application from your IDE or the terminal:
# Run directly using Maven wrapper
./mvnw spring-boot:run

# Or run the built JAR file
java -jar target/feed-normalizer-service.jar

### 2. Using Docker Compose
To run the application with Docker Compose, execute the following command from the project root:
docker-compose -f "docker-compose.yaml" up -d

