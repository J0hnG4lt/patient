package com.github.ehr.patient_microservice.configuration;


import static org.assertj.core.api.Assertions.assertThat;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
//@DataMongoTest
//@ContextConfiguration(classes = IntegrationTestMongoConfig.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class EmbeddedMongoTests {
    
    @DisplayName("Given object When save object using MongoDB template Then object can be found")
    @Test
    public void testEmbeddedMongo(@Autowired MongoTemplate mongoTemplate) {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
            .add("key", "value")
            .get();
        
        if (!mongoTemplate.collectionExists("collection")){
            mongoTemplate.createCollection("collection");
        }

        // when
        mongoTemplate.save(objectToSave, "collection");

        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
            .containsOnly("value");
    }
}