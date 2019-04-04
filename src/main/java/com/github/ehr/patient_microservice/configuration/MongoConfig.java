package com.github.ehr.patient_microservice.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

//import org.springframework.beans.factory.annotation.Autowired;
//mport org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile(value = "!test")
@Configuration
@EnableMongoRepositories(basePackages = "com.github.ehr.patient_microservice.repositories")
public class MongoConfig extends AbstractMongoConfiguration {
    
    //@Autowired
    //private Environment env;

    @Override
    protected String getDatabaseName() {
        return "patient_microservice";
    }

    @Override
    public MongoClient mongoClient() {
        
        return new MongoClient(
            new ServerAddress(
                "mongo", 27017
            ),
            MongoCredential.createCredential(
                "admin", 
                "admin", 
                "admin".toCharArray()
            ),
            MongoClientOptions.builder().build()
        );
    }


  
}
