# Patient Microservice

## Summary

This is a microservice that performs CRUD operations on Patient resources that are stored in a Mongo database.

## Endpoints

1. GET patient/{id}
2. GET patient/
3. POST patient/
4. PUT patient/{id}
5. PATCH patient/{id}
6. DELETE patient/{id}

## Technologies and tools

1. Spring-boot
2. Lombok
3. Mongo
4. Mongo Express
5. Docker

## Patterns

1. Microservices
2. Containerization with Docker
3. Test driven development with JUnit
4. Dependency Injection with Spring
5. Service discovery with Eureka (TODO)
6. Mongo Replication (TODO)
7. Mongo Sharding (TODO)

## Useful commands

1. mvn test
2. mvn compile
3. mvn package
4. mvn test -Dtest=PatientCrudControllerIntegrationT
ests#createAndPatchPatient
5. docker-compose up --build
6. java -jar target/patient_microservice.jar

## Standards

1. Fast Health Interoperability Resources (HL7 FHIR Standard): the patient resource is based on the patient definition specified in the FHIR v4.

## Sources

1. Healthcare Data Analytics (Chapman & Hall/CRC Data Mining and Knowledge Discovery Series)
2. [Spring Documentation](https://docs.spring.io)
3. [HL7 FHIR](https://www.hl7.org/fhir/)
4. [Project Lombok](https://projectlombok.org/)
5. Kasun Indrasiri, Prabath Siriwardena - Microservices for the Enterprise_ Designing, Developing, and Deploying (2018, Apress)

## Author

*Georvic Alejandro Tur Rojas*