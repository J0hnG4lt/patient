package com.github.ehr.patient_microservice.controllers;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ehr.patient_microservice.entities.Patient;

import org.springframework.util.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class PatientCrudControllerIntegrationTests {

	@Autowired
	MongoTemplate mongoTemplate;

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		mongoTemplate.dropCollection(Patient.class);
    }

	@Test
	public void createAndGetPatientTest() {

		ResponseEntity<String> getResponseEmpty = restTemplate.getForEntity(
			this.base.toString()+"patient", 
			String.class
		);

		Assert.isTrue(
			getResponseEmpty.getStatusCode().equals(HttpStatus.OK),
			"The response status was not OK"
		);

		HttpEntity<Patient> request = new HttpEntity<>(
			new Patient(Arrays.asList("Georvic", "Tur"))
		);

		URI location = restTemplate
		  .postForLocation(
			this.base.toString()+"patient", request
		);

		ResponseEntity<String> getResponse = restTemplate.getForEntity(
			this.base.toString()+"patient", 
			String.class
		);

		Assert.isTrue(
			getResponse.getStatusCode().equals(HttpStatus.OK),
			"The response status was not OK"
		);

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			ArrayList<Patient> patients = objectMapper.readValue(
				getResponse.getBody(),
				new TypeReference<List<Patient>>(){}
			);
			Assert.isTrue(
				patients.size() > 0,
				"The patient list should not be empty"
			);
			Assert.isTrue(
				patients.get(0).getName().get(0).equalsIgnoreCase("Georvic"),
				"Unexpected name"
			);
		} catch(IOException e) {
			fail(
				"The objectMapper for JSON failed", 
				e
			);
		} 
		
		mongoTemplate.dropCollection(Patient.class);
	}

}
