package com.github.ehr.patient_microservice.configuration;

import com.mongodb.MongoClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.github.ehr.patient_microservice.repositories")
public class MongoConfig extends AbstractMongoConfiguration {
    
    @Bean
    public MongoClient mongo() throws Exception {
        return new MongoClient("localhost");
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }
  
}
