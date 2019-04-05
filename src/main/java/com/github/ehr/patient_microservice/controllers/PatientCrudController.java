package com.github.ehr.patient_microservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.github.ehr.patient_microservice.repositories.PatientRepositoryInterface;
import com.github.ehr.patient_microservice.entities.Patient;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController("/patient")
public class PatientCrudController
{
    @Autowired PatientRepositoryInterface patientRepo;

    PatientCrudController(PatientRepositoryInterface patientRepo) {
        this.patientRepo = patientRepo;
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public List<Patient> listPatients(){
        List<Patient> patients = new ArrayList<>();
        Iterable<Patient> pIterator = patientRepo.findAll();
        
        pIterator.forEach(patients::add);

        return patients;
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public Patient getPatient(
        @PathVariable BigInteger id
    ){
        Optional<Patient> oPatient = patientRepo.findById(id);
        if (oPatient.isPresent()){
            Patient patient = (Patient) oPatient.get();
            return patient;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        
    }

    @RequestMapping(value = "/patient", method = RequestMethod.POST)
    public BigInteger createPatient(@RequestBody Patient patient){
        
        Patient storedPatient = patientRepo.save(patient);

        return storedPatient.getIdentity();
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public String deletePatient(
        @PathVariable BigInteger id
    ){
        
        Optional<Patient> oPatient = patientRepo.findById(id);
        if (oPatient.isPresent()){
            patientRepo.deleteById(id);
            return "ok";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PATCH)
    public String modifyPatient(
        @PathVariable BigInteger id,
        @RequestBody Map<String, Object> fields
    ){
        Optional<Patient> oPatient = patientRepo.findById(id);
        if (oPatient.isPresent()){
            // only update the fields given in the payload
            Patient storedPatient = oPatient.get();
            
            fields.forEach((k, v) -> {
                // use reflection to get field k on manager and set it to value k
                
                Field field = ReflectionUtils.findField(
                Patient.class, k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, storedPatient, v);
                
            });

            patientRepo.save(storedPatient);
            return "ok";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    public String updatePatient(
        @PathVariable BigInteger id,
        @RequestBody Patient patient
    ){
        Optional<Patient> oPatient = patientRepo.findById(id);
        if (oPatient.isPresent()){
            // insert the modified patient
            patient.setIdentity(id);
            patientRepo.save(patient);
            return "ok";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        
    }

}