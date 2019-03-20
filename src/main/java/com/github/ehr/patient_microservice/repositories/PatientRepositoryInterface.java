package com.github.ehr.patient_microservice.repositories;
 
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
 
import com.github.ehr.patient_microservice.entities.Patient;
 

public interface PatientRepositoryInterface extends CrudRepository<Patient, Long>
{
        @Query("{'name' : ?0}")
        public Iterable<Patient> searchByName(String personName);
 
}