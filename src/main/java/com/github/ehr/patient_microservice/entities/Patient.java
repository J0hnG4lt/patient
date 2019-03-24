package com.github.ehr.patient_microservice.entities;

import java.util.List;
import java.util.Date; 

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat; 

import lombok.Data;


@Document(collection="patient")
@Data
public class Patient
{
 
        @Id
        private Long identifier;
        
        private Boolean active;
        private List<String> name;
        private List<ContactPoint> telecom;
        private String gender;
        
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private Date birthDate;
        
        private Boolean deceasedBoolean;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private Date deceBooleanDateTime;
         
        private List<Address> addresses;
        
        private String maritalStatus;

        private List<Photo> photo;

        private List<Contact> contact;

        private List<Communication> communication;

        private Organization managingOrganization;

        private PatientLink link;

        public Patient()
        {}
         
         
        @PersistenceConstructor
        public Patient(Long personId, List<String> name)
        {
                super();
                this.identifier = personId;
                this.name = name;
        }
         
}