package com.github.ehr.patient_microservice.repositories;
 
import org.springframework.data.repository.CrudRepository;
 
import com.github.ehr.patient_microservice.entities.Address;
 

public interface AddressRepositoryInterface extends CrudRepository<Address, Long> {

}