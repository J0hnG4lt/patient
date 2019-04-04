package com.github.ehr.patient_microservice.controllers;

import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

import com.github.ehr.patient_microservice.entities.Patient;

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
import org.springframework.util.Assert;


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
		
		ResponseEntity<BigInteger> postResponse = restTemplate
		.postForEntity(
			this.base.toString()+"patient", 
			request, 
			BigInteger.class);

		BigInteger patientId = postResponse.getBody();
		Assert.isTrue(
			patientId.abs().compareTo(BigInteger.ZERO) == 1,
			"Unexpected ID for patient");

		ResponseEntity<Patient> getResponse = restTemplate.getForEntity(
			this.base.toString()+"patient/"+patientId.toString(), 
			Patient.class
		);

		Assert.isTrue(
			getResponse.getStatusCode().equals(HttpStatus.OK),
			"The response status was not OK"
		);

		Patient returnedPatient = getResponse.getBody();

		Assert.isTrue(
			returnedPatient.getName().get(0).equalsIgnoreCase("Georvic"),
			"Unexpected name"
		);

		Assert.isTrue(
			returnedPatient.getIdentity().equals(patientId),
			"Unexpected ID"
		);
		
		mongoTemplate.dropCollection(Patient.class);
	}

}
