package com.github.ehr.patient_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.github.ehr.patient_microservice"})
public class PatientMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientMicroserviceApplication.class, args);
	}

}
