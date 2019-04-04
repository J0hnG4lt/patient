package com.github.ehr.patient_microservice.repositories;

import java.util.Arrays;
import java.util.Optional;

import com.github.ehr.patient_microservice.configuration.IntegrationTestMongoConfig;
import com.github.ehr.patient_microservice.entities.Patient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { IntegrationTestMongoConfig.class })
@DataMongoTest
public class PatientRepositoryTests {

    @Autowired
    PatientRepositoryInterface patientRepo;

    // @Autowired 
    // MongoTemplate mongoTemplate;

    // @BeforeAll
    // public void init() throws Exception {
        
    //     this.mongoConnection = new EmbeddedMongoBuilder()
    //         .version("2.6.1")
    //         .bindIp("127.0.0.1")
    //         .port(27017)
    //         .build();
    // }

    // @AfterAll
    // public void finalize() {
    //     this.mongoConnection.close();
    // }

	@Test
	public void createPatientAndStoreTest() {
        Patient patient = new Patient(
            Arrays.asList(
                "Georvic", "Alejandro", "Tur", "Rojas"
            )
        );

        Patient storedPatient = patientRepo.save(patient);
        
        Optional<Patient> returnedPatientOptional = patientRepo.findById(
            storedPatient.getIdentity());
        
        Assert.isTrue(
            returnedPatientOptional.isPresent(),
            "The MongoDB did not return a patient");
        
        Patient returnedPatient = returnedPatientOptional.get();

        Assert.isTrue(
            returnedPatient.getIdentity()
            .equals(storedPatient.getIdentity()), 
            "Stored Patient's Identity is not equal to the queried patient");

        patientRepo.deleteById(storedPatient.getIdentity());
	}

}
