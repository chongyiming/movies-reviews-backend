# Use a base image with JDK
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy Maven/Gradle dependencies (change according to your build tool)
COPY target/*.jar app.jar

# Expose port (Spring Boot default is 8080)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]