package com.github.ehr.patient_microservice.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ehr.patient_microservice.entities.Patient;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class PatientCrudWithJsonIntegrationTests {

	@Autowired
	MongoTemplate mongoTemplate;

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

	private RestTemplate patchRestTemplate;

    @BeforeEach
    public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		mongoTemplate.dropCollection(Patient.class);

		//HACK for working with PATCH requests
        this.patchRestTemplate = restTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @AfterEach
    public void teadDown() throws Exception {
		mongoTemplate.dropCollection(Patient.class);
    }

    @Test
    public void createPatientFromJson() throws JsonParseException, JsonMappingException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Patient> typeReference = new TypeReference<Patient>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/postPatientSamplePayload.json");
        
        Patient newPatient = mapper.readValue(inputStream,typeReference);

		HttpEntity<Patient> postRequest = new HttpEntity<>(
			newPatient
		);
		
		// POST request for creating a new patient
		ResponseEntity<BigInteger> postResponse = restTemplate
		.postForEntity(
			this.base.toString()+"patient", 
			postRequest, 
			BigInteger.class);

		BigInteger patientId = postResponse.getBody();
		Assert.isTrue(
			patientId.abs().compareTo(BigInteger.ZERO) == 1,
			"Unexpected ID for patient");

		// GET request for patient that was recently created
		ResponseEntity<Patient> getResponse = restTemplate.getForEntity(
			this.base.toString()+"patient/"+patientId.toString(), 
			Patient.class
		);

		Assert.isTrue(
			getResponse.getStatusCode().equals(HttpStatus.OK),
			"The response status was not OK"
		);

		// check that it is the same patient
		Patient returnedPatient = getResponse.getBody();

		Assert.isTrue(
			returnedPatient.getName().equals(newPatient.getName()),
			"Unexpected name"
		);

		Assert.isTrue(
			returnedPatient.getIdentity().equals(patientId),
			"Unexpected ID"
		);
    
    }

}
