package com.github.ehr.patient_microservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.github.ehr.patient_microservice.repositories.PatientRepositoryInterface;
import com.github.ehr.patient_microservice.entities.Patient;

import java.util.ArrayList;
import java.util.List;
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
        @PathVariable Long id
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
    public String createPatient(@RequestBody Patient patient){
        
        patientRepo.save(patient);

        return "ok";
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public String deletePatient(
        @PathVariable Long id
    ){
        
        Optional<Patient> oPatient = patientRepo.findById(id);
        if (oPatient.isPresent()){
            patientRepo.deleteById(id);
            return "ok";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        
    }

}