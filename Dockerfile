# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/patient_microservice.jar

# Add the application's jar to the container
ADD ${JAR_FILE} patient_microservice.jar

# Run the jar file 
ENTRYPOINT ["java", "-jar","/patient_microservice.jar"]