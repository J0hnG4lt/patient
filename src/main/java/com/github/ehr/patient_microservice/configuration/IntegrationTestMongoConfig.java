package com.github.ehr.patient_microservice.configuration;

import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = "com.github.ehr.patient_microservice.repositories")
@Import(EmbeddedMongoAutoConfiguration.class)
@Profile(value = "test")
public class IntegrationTestMongoConfig {
  
}


